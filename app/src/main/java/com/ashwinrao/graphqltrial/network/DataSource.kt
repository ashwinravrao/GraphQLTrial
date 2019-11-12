package com.ashwinrao.graphqltrial.network

import com.ashwinrao.graphqltrial.util.ResponseCallback


interface DataSource {

    fun cancelRequest()

    fun fetchUsersMatching(param: String, callback: ResponseCallback)

}