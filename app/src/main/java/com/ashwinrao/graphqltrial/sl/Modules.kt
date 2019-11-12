package com.ashwinrao.graphqltrial.sl

import com.android.volley.toolbox.Volley
import com.ashwinrao.graphqltrial.network.DataSourceImpl
import com.ashwinrao.graphqltrial.repository.RepositoryImpl
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {

    single { Volley.newRequestQueue(androidContext()) }

    single { DataSourceImpl(get()) }

    single { RepositoryImpl(get()) }

}

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}