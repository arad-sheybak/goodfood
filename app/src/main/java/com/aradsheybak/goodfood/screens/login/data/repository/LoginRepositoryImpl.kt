package com.aradsheybak.goodfood.screens.login.data.repository

import com.aradsheybak.goodfood.screens.login.data.datasource.remote.AuthRemoteDataSource
import com.aradsheybak.goodfood.screens.login.data.mapper.toDomain
import com.aradsheybak.goodfood.screens.login.data.mapper.toRequestDto
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginCredentials
import com.aradsheybak.goodfood.screens.login.domain.entity.LoginResult
import com.aradsheybak.goodfood.screens.login.domain.repository.LoginRepository
import retrofit2.HttpException
import java.io.IOException

class LoginRepositoryImpl(val authRemoteDataSource: AuthRemoteDataSource) : LoginRepository {

    override suspend fun login(credentials: LoginCredentials): LoginResult {
        return try {
            val requestDto = credentials.toRequestDto()
            val responseDto = authRemoteDataSource.login(requestDto)
            LoginResult.Success(responseDto.toDomain())

        } catch (e: IOException) {
            LoginResult.Failure.NetworkError
        } catch (e: HttpException) {
            if (e.code() == 401) LoginResult.Failure.InvalidCredentials
            else LoginResult.Failure.Unknown(e)
        } catch (e: Exception) {
            LoginResult.Failure.Unknown(e)
        }
    }

}