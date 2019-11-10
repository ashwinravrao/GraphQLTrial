package com.ashwinrao.graphqltrial.viewmodel

import androidx.lifecycle.ViewModel
import com.ashwinrao.graphqltrial.network.User
import com.ashwinrao.graphqltrial.repository.RepositoryImpl

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {

    var users: List<User?>? = null
}