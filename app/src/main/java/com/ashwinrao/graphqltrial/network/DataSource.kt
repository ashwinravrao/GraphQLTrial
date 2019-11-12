package com.ashwinrao.graphqltrial.network

import com.ashwinrao.graphqltrial.util.ResponseCallback


interface DataSource {

    suspend fun cancelRequest()

    suspend fun fetchUsersMatching(param: String, callback: ResponseCallback)

}