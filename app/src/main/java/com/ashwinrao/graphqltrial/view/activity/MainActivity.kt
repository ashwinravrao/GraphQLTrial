package com.ashwinrao.graphqltrial.view.activity

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.ashwinrao.graphqltrial.BuildConfig
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.baseUrl
import com.ashwinrao.graphqltrial.databinding.ActivityMainBinding
import com.ashwinrao.graphqltrial.util.KeyboardUtil
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val tag = "GraphQLTrial"

class MainActivity : AppCompatActivity() {

    private val requestQueue: RequestQueue by inject()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        listenToSearchField(binding.searchField)
    }

    private fun listenToSearchField(editText: EditText) {

        editText.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_SEARCH)
            ) {
                if (v.text.isNotBlank()) searchUsers(v.text.toString())
                KeyboardUtil.hideKeyboard(this@MainActivity, v)
            }
            true
        }
    }

    private fun searchUsers(query: String) {
        val jsonObjectRequest =
            object : JsonObjectRequest(Method.POST, baseUrl, null, Response.Listener {
                Log.d(tag, it.toString())
            }, Response.ErrorListener {
                Log.e(tag, it.message ?: "Error communicating with GitHub\'s servers")
                Toast.makeText(this@MainActivity, "Oops. Try again later", Toast.LENGTH_SHORT)
                    .show()
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = "bearer ${BuildConfig.GITHUB_OAUTH_TOKEN}"
                    return headers
            }
        }
        requestQueue.add(jsonObjectRequest)
    }

//    private fun showFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .commit()
//    }

}
