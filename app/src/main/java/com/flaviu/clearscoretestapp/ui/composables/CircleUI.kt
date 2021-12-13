package com.flaviu.clearscoretestapp.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flaviu.clearscoretestapp.R
import com.flaviu.clearscoretestapp.ui.model.CreditScore

@Composable
@Preview
fun Loading() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.clear_score_demo))
        }, backgroundColor = colorResource(id = R.color.colorPrimaryLight))
    }, content = {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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
                )
        ) {

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.teal_700), modifier = Modifier.size(
                        dimensionResource(id = R.dimen.loader_circle_size)
                    )
                )
            }
        }
    })
}

@Composable
fun Error(onClickEvent: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.clear_score_demo))
        }, backgroundColor = colorResource(id = R.color.colorPrimaryLight))
    }, content = {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            MaterialTheme {
                Column {
                    AlertDialog(title = {
                        Text(
                            text = stringResource(id = R.string.error_msg)
                        )
                    }, text = {
                        Text(
                            text = stringResource(id = R.string.please_try_again_later)
                        )
                    }, onDismissRequest = {}, confirmButton = {
                        Button(onClick = { onClickEvent() }) {
                            Text(stringResource(R.string.ok))
                        }
                    })
                }
            }
        }
    })
}

@Preview
@Composable
fun Preview() {
    MainLayout(CreditScore(180, 1000, creditDetails = null)) {}
}

@Composable
fun InnerText(creditScore: CreditScore) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your credit score is",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = creditScore.score.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontSize = 70.sp,
            color = colorResource(
                id = (R.color.colorOrange)
            ),
        )
        Text(
            text = "out of ${creditScore.maxScore}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CircleProgressBar(creditScore: CreditScore) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) creditScore.score.toFloat() / creditScore.maxScore.toFloat() else 0f,
        animationSpec = tween(
            durationMillis = 4000,
            delayMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Canvas(
        Modifier
            .padding(dimensionResource(id = R.dimen.gutter_space_half))
            .size(dimensionResource(id = R.dimen.progress_bar_size))
    ) {
        drawArc(
            Color(0xFFFFA500),
            -90f,
            360 * curPercentage.value,
            false,
            style = Stroke(4.dp.toPx(), cap = StrokeCap.Round),
        )

    }
}

@Composable
fun CircleView(creditScore: CreditScore, onClickEvent: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .padding(dimensionResource(id = R.dimen.gutter_space))
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .border(
                    dimensionResource(id = R.dimen.outer_stroke_width), color = Color.Black,
                    CircleShape
                )
                .clickable {
                    onClickEvent()
                }
                .testTag("ClickedCircle")
        ) {
            CircleProgressBar(creditScore)
            InnerText(creditScore)
        }
    }
}

@Composable
fun MainLayout(creditScore: CreditScore, onClickEvent: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.clear_score_demo))
        }, backgroundColor = colorResource(id = R.color.colorPrimaryLight))
    }, content = {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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
                )
        ) {

            CircleView(creditScore = creditScore, onClickEvent)
        }
    })
}
