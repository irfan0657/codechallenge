package com.example.codechallenge.ui.main.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallenge.R
import com.example.codechallenge.data.network.ResponceResult
import com.example.codechallenge.databinding.FragmentSearchBinding
import com.example.codechallenge.ui.main.adapter.LongAdapter
import com.example.codechallenge.ui.main.viewmodel.SearchViewModel
import com.example.codechallenge.utils.Constants
import com.example.codechallenge.utils.NetworkUtil
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.searchViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.isIconified = false
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val longformRVAdapter = LongAdapter()
        binding.recyclerView.adapter = longformRVAdapter

        viewModel.searchResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResponceResult.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        binding.searchView,
                        it.errorMessage.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is ResponceResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    longformRVAdapter.submitList(it.data?.firstOrNull()?.lfs)
                }
                is ResponceResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        binding.searchView,
                        it.errorMessage.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    showSnackBar(getString(R.string.please_enter_minimum_3_characters))
                } else if (query.trim().isEmpty()) {
                    showSnackBar(getString(R.string.please_enter_minimum_3_characters))
                } else if (query.length >= Constants.THREE) {
                    if (context?.let { NetworkUtil.isOnline(it.applicationContext) } == true) {
                        binding.progressBar.visibility = View.VISIBLE
                        viewModel.getSearchItem(query)
                    } else {
                        showSnackBar(getString(R.string.please_check_internet_connection))
                    }
                } else {
                    showSnackBar(getString(R.string.please_enter_minimum_3_characters))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    longformRVAdapter.submitList(null)
                }
                return false
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.searchView, message, Snackbar.LENGTH_LONG).show()
    }
}