package com.ashwinrao.graphqltrial.repository

import com.ashwinrao.graphqltrial.util.ResponseCallback


interface Repository {

    fun fetchUsersMatching(param: String, callback: ResponseCallback)

    fun cancelRequest()

}