package com.flaviu.clearscoretestapp.utils

import com.flaviu.clearscoretestapp.ui.model.CreditScore


interface DataState {

    object Loading : DataState
    data class CreditScoreLoaded(val creditScore: CreditScore?) : DataState
    object Error : DataState
}