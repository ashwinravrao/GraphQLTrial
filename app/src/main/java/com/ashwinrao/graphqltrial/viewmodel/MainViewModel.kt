package com.ashwinrao.graphqltrial.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import com.ashwinrao.graphqltrial.network.User
import com.ashwinrao.graphqltrial.repository.RepositoryImpl
import com.ashwinrao.graphqltrial.util.ResponseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject

private const val tag = "MainViewModel"

class MainViewModel(private val repository: RepositoryImpl) : ViewModel(), ResponseCallback {

    private val _listOfUsers: MutableList<User>? = mutableListOf()
    private val _usersResponseObject = MutableLiveData<MutableList<User>?>()
    val usersResponseObject: LiveData<MutableList<User>?>
        get() = _usersResponseObject

    fun fetchUsersMatching(param: String) {
        repository.fetchUsersMatching(param, this@MainViewModel)
    }

    fun cancelRequest() {
        repository.cancelRequest()
    }

    private fun parseJsonResponse(response: JSONObject) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val nodesArray =
                    response.getJSONObject("data").getJSONObject("search").getJSONArray("nodes")
                for (i in 0 until nodesArray.length()) {
                    val userObject: JSONObject? = nodesArray.getJSONObject(i)
                    if (userObject != null) {
                        val user = User(
                            userObject.getString("name"),
                            userObject.getString("bio")
                        )
                        withContext(Dispatchers.Main) {
                            _listOfUsers?.add(user)
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    _usersResponseObject.value = _listOfUsers
                }
            } catch (e: JSONException) {
                withContext(Dispatchers.Main) {
                    Log.e(tag, e.message ?: "Error parsing JSON response object")
                }
            }
        }
    }

    override fun onSuccess(response: JSONObject) {
        _listOfUsers?.clear()
        parseJsonResponse(response)
        Log.d(tag, "Response object received")
    }

    override fun onError(error: VolleyError) {
        _usersResponseObject.value = null
        Log.e(tag, error.message ?: "Error making api request")
    }

}