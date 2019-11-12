package com.ashwinrao.graphqltrial.view.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.databinding.ActivityMainBinding
import com.ashwinrao.graphqltrial.network.User
import com.ashwinrao.graphqltrial.util.KeyboardUtil
import com.ashwinrao.graphqltrial.view.fragment.ListFragment
import com.ashwinrao.graphqltrial.view.fragment.PlaceholderFragment
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )
        listenToSearchField(binding.searchField)
        listenToResponseLiveData(viewModel.usersResponseObject)
    }

    override fun onPause() {
        super.onPause()
        viewModel.cancelRequest()
    }

    private fun listenToSearchField(editText: EditText) {
        editText.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_SEARCH)
            ) {
                if (v.text.isNotBlank()) {
                    viewModel.fetchUsersMatching(v.text.toString())
                }
                KeyboardUtil.hideKeyboard(this@MainActivity, v)
            }
            false
        }
    }

    private fun listenToResponseLiveData(responseObject: LiveData<MutableList<User>?>) {
        responseObject.observe(this@MainActivity, Observer {
            showFragment(
                if (it != null) ListFragment()
                else PlaceholderFragment()
            )
        })
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}
