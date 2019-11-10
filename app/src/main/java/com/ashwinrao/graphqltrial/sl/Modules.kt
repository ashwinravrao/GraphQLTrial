package com.ashwinrao.graphqltrial.sl

import com.apollographql.apollo.ApolloClient
import com.ashwinrao.graphqltrial.BuildConfig
import com.ashwinrao.graphqltrial.baseUrl
import com.ashwinrao.graphqltrial.network.DataSourceImpl
import com.ashwinrao.graphqltrial.repository.RepositoryImpl
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {

    single {
        OkHttpClient.Builder().addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "bearer ${BuildConfig.GITHUB_OAUTH_TOKEN}")
                .build()
            chain.proceed(request)
        }.build()
    }

    single {
        ApolloClient.builder()
            .serverUrl(baseUrl)
            .okHttpClient(get())
            .build()
    }

    single { DataSourceImpl(get()) }

    single { RepositoryImpl(get()) }

}

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}