package com.ashwinrao.graphqltrial.repository

import com.ashwinrao.graphqltrial.network.DataSourceImpl

class RepositoryImpl(
    private val dataSource: DataSourceImpl
) : Repository