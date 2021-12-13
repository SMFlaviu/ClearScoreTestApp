package com.flaviu.clearscoretestapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditScore(
    val score: Int = 0,
    val maxScore: Int = 0,
    val creditDetails: CreditDetails?
) : Parcelable