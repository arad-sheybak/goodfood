package com.aradsheybak.goodfood.screens.signup.data.repository

import com.aradsheybak.goodfood.screens.signup.data.datasource.remote.SignupRemoteDataSource
import com.aradsheybak.goodfood.screens.signup.data.mapper.toDomain
import com.aradsheybak.goodfood.screens.signup.data.mapper.toRequestDto
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupCredentials
import com.aradsheybak.goodfood.screens.signup.domain.entity.SignupResult
import com.aradsheybak.goodfood.screens.signup.domain.repository.SignupRepository
import retrofit2.HttpException
import java.io.IOException

class SignupRepositoryImpl(val signupRemoteDataSource: SignupRemoteDataSource) : SignupRepository {
    override suspend fun signup(credentials: SignupCredentials): SignupResult {
        return try {
            val requestDto = credentials.toRequestDto()
            val responseDto = signupRemoteDataSource.signup(request = requestDto)
            responseDto.toDomain()
        } catch (e: IOException) {
            SignupResult.Failure.NetworkError
        } catch (e: HttpException) {
            if (e.code() == 401) SignupResult.Failure.InvalidEmail
            else if (e.code() == 405) SignupResult.Failure.EmailAlreadyExists
            else SignupResult.Failure.Unknown(e)
        }
    }

}