package com.flaviu.clearscoretestapp

import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.flaviu.clearscoretestapp.ui.CreditScoreActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.InputStreamReader

@HiltAndroidTest
internal class ClearScoreCircleActivitySucces {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testRule = createEmptyComposeRule()

    private lateinit var mockWebServer: MockWebServer

    private lateinit var scenario: ActivityScenario<CreditScoreActivity>

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.url("http://localhost")
        mockWebServer.dispatcher = successDispatcher()

        scenario = ActivityScenario.launch(CreditScoreActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun clear_score_MainActivity_test() {
        testRule.onNodeWithText(context.getString(R.string.clear_score_demo)).assertExists()

        testRule.waitForIdle()

        testRule.onNodeWithText(context.getString(R.string.clear_score_demo)).assertExists()
        testRule.onNodeWithText(context.getString(R.string.your_credit_score_is)).assertExists()
        val circleCompose = testRule.onNodeWithTag("ClickedCircle")
        circleCompose.assertHasClickAction()
        circleCompose.performClick()

        testRule.waitForIdle()
    }


    private fun successDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(getJsonContent())
            }
        }
    }

    private fun getJsonContent(): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream("FakeData.json")).use { it.readText() }
    }
}