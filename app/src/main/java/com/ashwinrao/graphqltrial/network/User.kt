package com.ashwinrao.graphqltrial.network

import java.util.*

data class User(val id: String = UUID.randomUUID().toString(), val name: String, val bio: String)