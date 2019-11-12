package com.ashwinrao.graphqltrial.repository

import com.ashwinrao.graphqltrial.util.ResponseCallback


interface Repository {

    suspend fun fetchUsersMatching(param: String, callback: ResponseCallback)

    suspend fun cancelRequest()

}