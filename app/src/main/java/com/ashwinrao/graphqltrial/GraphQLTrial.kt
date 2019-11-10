package com.ashwinrao.graphqltrial

import android.app.Application
import com.ashwinrao.graphqltrial.sl.networkModule
import com.ashwinrao.graphqltrial.sl.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class GraphQLTrial : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GraphQLTrial)
            modules(listOf(networkModule, viewModelModule))
        }
    }

}