package com.flaviu.clearscoretestapp.ui.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaviu.clearscoretestapp.data.IMainRepository
import com.flaviu.clearscoretestapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val creditScoreRepository: IMainRepository
) :
    ViewModel() {

    val dataViewState = MutableLiveData<DataState>()

    fun fetchCreditScore() {
        dataViewState.value = DataState.Loading
        CoroutineScope(IO).launch {
            val response = creditScoreRepository.getCreditScoreAsync().await()
            if (response != null) {
                if (response.score != 0 && response.maxScore != 0) {
                    dataViewState.postValue(DataState.CreditScoreLoaded(response))
                } else {
                    dataViewState.postValue(DataState.Error)
                }
            }
        }
    }
}
