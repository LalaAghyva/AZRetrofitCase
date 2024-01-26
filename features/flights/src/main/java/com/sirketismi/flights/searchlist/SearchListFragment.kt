package com.sirketismi.flights.searchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirketismi.common.base.BaseFragment
import com.sirketismi.common.flowstate.Status
import com.sirketismi.flights.databinding.FragmentSearchListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchListBinding, SearchListViewModel>(FragmentSearchListBinding::inflate) {
    val viewModel : SearchListViewModel by viewModels()
    private lateinit var adapter: SearchListAdapter

    override fun mViewModel(): SearchListViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerAdapter()

            lifecycleScope.launch {
                viewModel.getFlights()
            }


        viewModel.data.observe(viewLifecycleOwner) {
            adapter.setData(it?.flights ?: listOf())
        }
    }


    private fun initRecylerAdapter() {
        adapter = SearchListAdapter()
        binding.recylerView.layoutManager = LinearLayoutManager(context)
        binding.recylerView.adapter = adapter
    }


}