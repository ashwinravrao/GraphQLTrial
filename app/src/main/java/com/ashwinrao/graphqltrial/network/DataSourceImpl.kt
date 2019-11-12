package com.ashwinrao.graphqltrial.network

import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.ashwinrao.graphqltrial.BuildConfig
import com.ashwinrao.graphqltrial.baseUrl
import com.ashwinrao.graphqltrial.util.ResponseCallback
import org.json.JSONObject

private const val requestTag = "fetch matching users"

class DataSourceImpl(private val requestQueue: RequestQueue) : DataSource {

    private fun buildUsersQuery(param: String): JSONObject =
        JSONObject(
            "{\"query\":\"query(\$name: String!) " +
                    "{search(first: 50, query: \$name, type: USER) " +
                    "{nodes {... UserFragment}}} fragment UserFragment on User {name bio}\"," +
                    "\"variables\":{\"name\":\"$param\"}}"
        )

    override suspend fun fetchUsersMatching(param: String, callback: ResponseCallback) {
        val jsonObjectRequest =
            object : JsonObjectRequest(
                Method.POST, "$baseUrl?query=", buildUsersQuery(param),
                Response.Listener {
                    callback.onSuccess(it)
                },
                Response.ErrorListener {
                    callback.onError(it)
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    headers["Authorization"] = "bearer ${BuildConfig.GITHUB_OAUTH_TOKEN}"
                    return headers
                }
            }
        jsonObjectRequest.tag = requestTag
        requestQueue.add(jsonObjectRequest)
    }

    override suspend fun cancelRequest() {
        requestQueue.cancelAll(requestTag)
    }

}