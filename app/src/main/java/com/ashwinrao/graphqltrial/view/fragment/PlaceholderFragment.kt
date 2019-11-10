package com.ashwinrao.graphqltrial.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.databinding.FragmentPlaceholderBinding

class PlaceholderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentPlaceholderBinding>(inflater, R.layout.fragment_placeholder, container, false).root
    }

}