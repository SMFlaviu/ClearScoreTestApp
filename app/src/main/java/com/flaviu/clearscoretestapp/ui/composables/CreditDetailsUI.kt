package com.flaviu.clearscoretestapp.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flaviu.clearscoretestapp.R
import com.flaviu.clearscoretestapp.ui.model.CreditDetails
import com.flaviu.clearscoretestapp.ui.model.CreditScore

@Composable
fun MainLayout(creditScore: CreditScore) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.credit_details))
        }, backgroundColor = colorResource(id = R.color.colorPrimaryLight))
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            colorResource(id = R.color.background),
                            colorResource(id = R.color.background2),
                            colorResource(id = R.color.background3),
                            colorResource(id = R.color.background4),
                        )
                    )
                ), verticalArrangement = Arrangement.Top
        ) {

            if (creditScore.creditDetails != null) {
                CreditDetailsCard(creditDetails = creditScore.creditDetails)
            }
        }
    })
}

@Composable
private fun CreditDetailsCard(creditDetails: CreditDetails) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        backgroundColor = colorResource(id = R.color.teal_700),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.credit_details_card),
                    style = MaterialTheme.typography.h4.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold

                    )
                )
                if (expanded) {
                    Text(
                        text = ("Changed score :" + creditDetails.changedScore.toString()),
                    )
                    Text(
                        text = ("Equifax Score Band Description :" + creditDetails.equifaxScoreBandDescription),
                    )
                    Text(
                        text = ("Negative Score Factors :" + creditDetails.numNegativeScoreFactors.toString()),
                    )
                    Text(
                        text = ("Positive Score Factors :" + creditDetails.numPositiveScoreFactors.toString()),
                    )
                    Text(
                        text = ("Days until next report :" + creditDetails.daysUntilNextReport.toString()),
                    )
                    Text(
                        text = ("Status " + creditDetails.status),
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }

                )
            }
        }
    }
}


//@Composable
//@Preview
//fun Preview() {
//    val creditScore = CreditScore(557, 700, CreditDetails())
//    MainLayout(creditScore = creditScore)
//}