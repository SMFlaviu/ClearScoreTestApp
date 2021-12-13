package com.flaviu.clearscoretestapp.data

import com.flaviu.clearscoretestapp.ui.model.CreditScore
import kotlinx.coroutines.Deferred

interface IMainRepository {
    suspend fun getCreditScoreAsync(): Deferred<CreditScore?>
}