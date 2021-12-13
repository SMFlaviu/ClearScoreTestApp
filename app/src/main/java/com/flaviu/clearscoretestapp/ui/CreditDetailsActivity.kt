package com.flaviu.clearscoretestapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.flaviu.clearscoretestapp.ui.composables.MainLayout
import com.flaviu.clearscoretestapp.ui.model.CreditScore

class CreditDetailsActivity : AppCompatActivity() {

    companion object {
        const val CREDIT_DETAILS_DATA = "CREDIT_DETAILS_DATA"

        fun getStartIntent(context: Context, creditScore: CreditScore): Intent {
            return Intent(context, CreditDetailsActivity::class.java).apply {
                putExtra(CREDIT_DETAILS_DATA, creditScore)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras != null && intent.extras!!.get(CREDIT_DETAILS_DATA) != null) {
            val creditScore = intent!!.extras!!.get(CREDIT_DETAILS_DATA) as CreditScore
            setContent {
                MainLayout(creditScore = creditScore)
            }
        }
    }
}
