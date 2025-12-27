package com.aradsheybak.goodfood.di

import com.aradsheybak.goodfood.data.datastore.PreferencesManager
import com.aradsheybak.goodfood.screens.login.data.datasource.remote.AuthRemoteDataSource
import com.aradsheybak.goodfood.screens.login.data.datasource.remote.AuthApi
import com.aradsheybak.goodfood.screens.login.data.repository.LoginRepositoryImpl
import com.aradsheybak.goodfood.screens.login.domain.repository.LoginRepository
import com.aradsheybak.goodfood.screens.login.domain.usecase.LoginUseCase
import com.aradsheybak.goodfood.screens.login.presentation.LoginViewModel
import com.aradsheybak.goodfood.screens.signup.data.datasource.remote.SignupApi
import com.aradsheybak.goodfood.screens.signup.data.datasource.remote.SignupRemoteDataSource
import com.aradsheybak.goodfood.screens.signup.data.repository.SignupRepositoryImpl
import com.aradsheybak.goodfood.screens.signup.domain.repository.SignupRepository
import com.aradsheybak.goodfood.screens.signup.domain.usecase.SignupUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val appModule = module {
    //Context
    single { androidContext() }

    // data source preferences
    single { PreferencesManager(context = get()) }

    // Moshi
    single {
        Moshi.Builder().build()
    }

    //okhttp with 30 sec time out
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://yourapi.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    // ########### ApiInterfaces ###########

    //Login
    single {
        get<Retrofit>().create(AuthApi::class.java)
    }

    //Signup
    single { get<Retrofit>().create(SignupApi::class.java) }






    // ########### RemoteDataSources ###########

    //Login
    single {
        AuthRemoteDataSource(get())
    }
    //signup
    single { SignupRemoteDataSource(get()) }





    // ########### Repositories ###########

    //Login
    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }
    //signup
    single<SignupRepository> {
        SignupRepositoryImpl(get())
    }




    // ########### UseCases ###########

    //Login
    single {
        LoginUseCase(get())
    }
    //signup
    single { SignupUseCase(get()) }


    // ########### ViewModels ###########

    //login
    viewModel {
        LoginViewModel(get())
    }
}