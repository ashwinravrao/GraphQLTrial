package com.ashwinrao.graphqltrial.repository

import com.apollographql.apollo.graphqltrial.fragment.RepositoryFragment
import com.apollographql.apollo.graphqltrial.fragment.UserFragment
import com.ashwinrao.graphqltrial.network.DataSourceImpl

class RepositoryImpl(
    private val dataSource: DataSourceImpl
) : Repository {

    override suspend fun fetchMatchingUsers(query: String): List<UserFragment?> =
        dataSource.fetchMatchingUsers(query)

    override suspend fun fetchRepositories(): List<RepositoryFragment> =
        dataSource.fetchRepositories()
}