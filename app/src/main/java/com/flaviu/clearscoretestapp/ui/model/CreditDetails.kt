package com.flaviu.clearscoretestapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditDetails(
    val numNegativeScoreFactors: Int = 0,
    val numPositiveScoreFactors: Int = 0,
    val daysUntilNextReport: Int = 0,
    val changedScore: Int = 0,
    val status: String = "",
    val equifaxScoreBandDescription: String = ""
) : Parcelable