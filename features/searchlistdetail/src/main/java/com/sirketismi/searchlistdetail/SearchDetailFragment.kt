package com.sirketismi.searchlistdetail

import android.os.Bundle
import com.sirketismi.common.base.BaseFragment
import com.sirketismi.common.base.BaseViewModel
import com.sirketismi.searchlistdetail.databinding.FragmentSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment: BaseFragment<FragmentSearchDetailBinding>(FragmentSearchDetailBinding::inflate) {
    val viewModel : SearchDetailViewModel by viewModels()
    val args: SearchDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val itemId = it.getString("itemId")
        }

        binding.layoutUser.txtName
    }
    override fun mViewModel(): BaseViewModel {
        return viewModel
    }
}