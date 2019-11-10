package com.ashwinrao.graphqltrial.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashwinrao.graphqltrial.R
import com.ashwinrao.graphqltrial.databinding.FragmentListBinding
import com.ashwinrao.graphqltrial.util.ItemDecoration
import com.ashwinrao.graphqltrial.view.adapter.UserAdapter
import com.ashwinrao.graphqltrial.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )
        initializeRecyclerView(binding.recyclerView)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(viewModel.users)
    }

    private fun initializeRecyclerView(rv: RecyclerView) {
        adapter = UserAdapter(requireContext())
        rv.setHasFixedSize(true)
        ItemDecoration.addItemDecoration(requireContext(), rv, 1, 16f)
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }

}