package com.ashwinrao.graphqltrial.viewmodel

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.graphqltrial.fragment.UserFragment
import com.ashwinrao.graphqltrial.repository.RepositoryImpl

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {

    var users: List<UserFragment?>? = null

    suspend fun fetchMatchingUsers(query: String): List<UserFragment?>? {
        users = repository.fetchMatchingUsers(query)
        return users
    }
}