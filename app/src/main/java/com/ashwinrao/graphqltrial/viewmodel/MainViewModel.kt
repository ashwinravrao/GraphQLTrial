package com.ashwinrao.graphqltrial.viewmodel

import androidx.lifecycle.ViewModel
import com.ashwinrao.graphqltrial.repository.RepositoryImpl
import com.ashwinrao.graphqltrial.util.ResponseCallback
import org.json.JSONObject

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {

    var usersResponseObject: JSONObject? = null

    suspend fun fetchUsersMatching(param: String, callback: ResponseCallback) =
        repository.fetchUsersMatching(param, callback)

    suspend fun cancelRequest() = repository.cancelRequest()

}