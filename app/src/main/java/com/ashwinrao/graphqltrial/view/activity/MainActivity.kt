package com.ashwinrao.graphqltrial.view.activity

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.ashwinrao.graphqltrial.databinding.ActivityMainBinding
import com.ashwinrao.graphqltrial.util.KeyboardUtil
import com.ashwinrao.graphqltrial.util.ResponseCallback
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val tag = "GraphQLTrial"

class MainActivity : AppCompatActivity(), ResponseCallback {

    private val requestQueue: RequestQueue by inject()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                com.ashwinrao.graphqltrial.R.layout.activity_main
            )
        listenToSearchField(binding.searchField)
    }

    override fun onPause() {
        super.onPause()
        CoroutineScope(Dispatchers.IO).launch { viewModel.cancelRequest() }
    }

    private fun listenToSearchField(editText: EditText) {

        editText.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_SEARCH)
            ) {
                if (v.text.isNotBlank()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.fetchUsersMatching(v.text.toString(), this@MainActivity)
                    }
                }
                KeyboardUtil.hideKeyboard(this@MainActivity, v)
            }
            false
        }
    }

    override fun onSuccess(response: JSONObject) {
        Log.d(tag, response.toString())
    }

    override fun onError(error: VolleyError) {
        Log.e(tag, error.message ?: "Error during api request")
    }

}
