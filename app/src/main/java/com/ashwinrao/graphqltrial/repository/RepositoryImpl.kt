package com.ashwinrao.graphqltrial.repository

import com.ashwinrao.graphqltrial.network.DataSourceImpl
import com.ashwinrao.graphqltrial.util.ResponseCallback

class RepositoryImpl(private val dataSource: DataSourceImpl) : Repository {

    override fun fetchUsersMatching(param: String, callback: ResponseCallback) =
        dataSource.fetchUsersMatching(param, callback)

    override fun cancelRequest() = dataSource.cancelRequest()

}