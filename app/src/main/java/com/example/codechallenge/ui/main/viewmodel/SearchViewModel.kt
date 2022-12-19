package com.example.codechallenge.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val searchResponseLiveData get() = mainRepository.searchResponseLiveData
    fun getSearchItem(sf: String) {
        viewModelScope.launch {
            mainRepository.getAcromineResult(sf)
        }
    }
}