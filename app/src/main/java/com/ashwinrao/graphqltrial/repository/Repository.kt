package com.ashwinrao.graphqltrial.repository

import com.apollographql.apollo.graphqltrial.fragment.RepositoryFragment
import com.apollographql.apollo.graphqltrial.fragment.UserFragment

interface Repository {

    suspend fun fetchRepositories(): List<RepositoryFragment>

    suspend fun fetchMatchingUsers(query: String): List<UserFragment?>

}