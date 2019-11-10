package com.ashwinrao.graphqltrial.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.apollographql.apollo.graphqltrial.fragment.UserFragment
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.databinding.ActivityMainBinding
import com.ashwinrao.graphqltrial.util.KeyboardUtil
import com.ashwinrao.graphqltrial.view.fragment.ListFragment
import com.ashwinrao.graphqltrial.view.fragment.PlaceholderFragment
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var job: Job
    private var jobCancelled: Boolean = false
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
        job = CoroutineScope(Dispatchers.IO).launch {
            val users: List<UserFragment?>? = viewModel.fetchMatchingUsers(query)
            withContext(Dispatchers.Main) {
                showFragment(
                    if (users.isNullOrEmpty()) PlaceholderFragment()
                    else ListFragment()
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (jobCancelled) job.start()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
        jobCancelled = true
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}
