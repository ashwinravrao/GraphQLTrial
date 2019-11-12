package com.ashwinrao.graphqltrial.network

import kotlin.random.Random

data class User(val name: String, val bio: String?) {
    val id: Long = Random.nextLong()
}