package com.flaviu.clearscoretestapp


import android.content.Context
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createEmptyComposeRule
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

@HiltAndroidTest
class ClearScoreErrorActivity {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockWebServer: MockWebServer
    private val context: Context = ApplicationProvider.getApplicationContext()

    private lateinit var scenario: ActivityScenario<CreditScoreActivity>

    @get:Rule
    val errorRule = createEmptyComposeRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.url("http://localhost")
        mockWebServer.dispatcher = errorDispatcher()

        scenario = ActivityScenario.launch(CreditScoreActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun displayErrorViewStateAndCloseApp() {
        errorRule.onNodeWithText(context.getString(R.string.clear_score_demo)).assertExists()
        errorRule.waitForIdle()
        errorRule.onNodeWithText(context.getString(R.string.error_msg)).assertExists()
        errorRule.onNodeWithText(context.getString(R.string.please_try_again_later)).assertExists()
        val button = errorRule.onNodeWithText(context.getString(R.string.ok))
        button.assertHasClickAction()
        button.performClick()
    }

    private fun errorDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }
    }
}

