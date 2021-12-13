package com.flaviu.clearscoretestapp.data

import com.flaviu.clearscoretestapp.network.DataResponse
import com.flaviu.clearscoretestapp.ui.model.CreditDetails
import com.flaviu.clearscoretestapp.ui.model.CreditScore

object DataNetworkMapper {

    fun mapCreditScore(dataResponse: DataResponse): CreditScore {
        val creditScoreDetails = dataResponse.creditReportInfo

        val creditDetails = CreditDetails(
            creditScoreDetails?.numNegativeScoreFactors ?: 0,
            creditScoreDetails?.numPositiveScoreFactors ?: 0,
            creditScoreDetails?.daysUntilNextReport ?: 0,
            creditScoreDetails?.changedScore ?: 0,
            creditScoreDetails?.status ?: "Not enough data",
            creditScoreDetails?.equifaxScoreBandDescription ?: "Not enough data",
        )

        return CreditScore(
            creditScoreDetails?.score ?: 0,
            creditScoreDetails?.maxScoreValue ?: 0,
            creditDetails

        )
    }
}