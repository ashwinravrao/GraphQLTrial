package com.ashwinrao.graphqltrial.network

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.graphqltrial.GithubRepositoriesQuery
import com.apollographql.apollo.graphqltrial.UserQuery
import com.apollographql.apollo.graphqltrial.fragment.RepositoryFragment
import com.apollographql.apollo.graphqltrial.fragment.UserFragment

interface DataSource {

    val repositoriesQuery: GithubRepositoriesQuery

    fun buildUserQuery(query: String) : UserQuery

    suspend fun fetchRepositories(): List<RepositoryFragment>

    suspend fun fetchMatchingUsers(query: String) : List<UserFragment?>

    fun mapRepositoriesResponseToRepositories(response: Response<GithubRepositoriesQuery.Data>): List<RepositoryFragment> {
        return response.data()?.viewer()?.repositories()?.nodes()?.map {
            it.fragments().repositoryFragment()
        } ?: emptyList()
    }

    fun mapUsersResponseToUsers(response: Response<UserQuery.Data>): List<UserFragment?> {
        return response.data()?.search()?.nodes()?.map { it.fragments().userFragment() } ?: emptyList()
    }
}