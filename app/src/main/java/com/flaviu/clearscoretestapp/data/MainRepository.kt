package com.flaviu.clearscoretestapp.data

import com.flaviu.clearscoretestapp.network.APIRequest
import com.flaviu.clearscoretestapp.ui.model.CreditScore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val provideCreditScoreAPIRequest: APIRequest
) :
    IMainRepository {
    override suspend fun getCreditScoreAsync(): Deferred<CreditScore?> {
        return CoroutineScope(IO).async {
            val response = provideCreditScoreAPIRequest.getDataResponse()
            if (response.isSuccessful && response.body() != null) {
                DataNetworkMapper.mapCreditScore(response.body()!!)
            } else {
                CreditScore(creditDetails = null)
            }
        }
    }
}