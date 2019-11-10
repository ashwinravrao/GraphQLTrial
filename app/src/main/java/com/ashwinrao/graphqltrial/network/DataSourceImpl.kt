package com.ashwinrao.graphqltrial.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.graphqltrial.GithubRepositoriesQuery
import com.apollographql.apollo.graphqltrial.UserQuery
import com.apollographql.apollo.graphqltrial.fragment.RepositoryFragment
import com.apollographql.apollo.graphqltrial.fragment.UserFragment
import com.apollographql.apollo.graphqltrial.type.OrderDirection
import com.apollographql.apollo.graphqltrial.type.RepositoryOrderField

class DataSourceImpl(private val apolloClient: ApolloClient) : DataSource {

    override val repositoriesQuery: GithubRepositoriesQuery = GithubRepositoriesQuery.builder()
        .repositoriesCount(50)
        .orderBy(RepositoryOrderField.UPDATED_AT)
        .orderDirection(OrderDirection.DESC)
        .build()

    override fun buildUserQuery(query: String): UserQuery =
        UserQuery.builder().searchResultsCount(50).username(query).build()

    override suspend fun fetchRepositories(): List<RepositoryFragment> =
        mapRepositoriesResponseToRepositories(
            apolloClient
                .query(repositoriesQuery)
                .toDeferred()
                .await()
        )

    override suspend fun fetchMatchingUsers(query: String): List<UserFragment?> =
        mapUsersResponseToUsers(
            apolloClient.query(buildUserQuery(query)).toDeferred().await()
        )

}