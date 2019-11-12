package com.ashwinrao.graphqltrial.util

import com.android.volley.VolleyError
import org.json.JSONObject

interface ResponseCallback {

    fun onSuccess(response: JSONObject)

    fun onError(error: VolleyError)

}