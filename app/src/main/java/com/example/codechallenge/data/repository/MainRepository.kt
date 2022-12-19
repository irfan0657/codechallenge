package com.example.codechallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codechallenge.data.model.AcromineResponse
import com.example.codechallenge.data.network.ResponceResult
import com.example.codechallenge.data.network.WebApi
import javax.inject.Inject
/**
 * This MainRepository provides the getAcromineResult(sf: String) function for fetching the details of acromine from api.
 */
class MainRepository @Inject constructor(private val webApi: WebApi) {
    /**
     * observing the responce mutableLiveData
     */
    private val _acromineResponseLiveData = MutableLiveData<ResponceResult<ArrayList<AcromineResponse.AcromineResponseItem>>>()
    val searchResponseLiveData: LiveData<ResponceResult<ArrayList<AcromineResponse.AcromineResponseItem>>>
        get() = _acromineResponseLiveData

    suspend fun getAcromineResult(sf: String) {
        val result = webApi.getAcromine(sf)
        if (result.body() != null) {
            if (result.body()!!.isEmpty()) {
                _acromineResponseLiveData.postValue(ResponceResult.Error("No results found for \"$sf\""))
            } else
                _acromineResponseLiveData.postValue(ResponceResult.Success(result.body()))
        } else if (result.errorBody() != null) {
            _acromineResponseLiveData.postValue(ResponceResult.Error(result.message()))
        } else {
            _acromineResponseLiveData.postValue(ResponceResult.Error("Something went wrong"))
        }
    }
}