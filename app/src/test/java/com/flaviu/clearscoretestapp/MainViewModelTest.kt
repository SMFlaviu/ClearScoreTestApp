package com.flaviu.clearscoretestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flaviu.clearscoretestapp.data.IMainRepository
import com.flaviu.clearscoretestapp.ui.model.CreditScore
import com.flaviu.clearscoretestapp.ui.model.MainViewModel
import com.flaviu.clearscoretestapp.utils.DataState
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

        @Mock
        lateinit var repository: IMainRepository

    @get:Rule
        var rule: TestRule = InstantTaskExecutorRule()

        @Test
        fun `Testing onSucces state`() = runBlocking {
            val viewModel = MainViewModel(repository)
            val stateList = arrayListOf<DataState>()
            val score = CreditScore(33, 700)

            `when`(repository.getCreditScoreAsync()).thenReturn(CompletableDeferred((score)))

            viewModel.dataViewState.observeForever {
                stateList.add(it)
            }
            viewModel.fetchCreditScore()

            Thread.sleep(2000)
            Assert.assertEquals(DataState.Loading, stateList[0])
            Assert.assertEquals(DataState.CreditScoreLoaded(score), stateList[1])
        }

        @Test
        fun `Testing error state`() = runBlocking {
            val viewModel = MainViewModel(repository)
            val states = arrayListOf<DataState>()
            `when`(repository.getCreditScoreAsync()).thenReturn(
                CompletableDeferred(
                    CreditScore(creditDetails = null)
                )
            )

            viewModel.dataViewState.observeForever {
                states.add(it)
            }
            viewModel.fetchCreditScore()

            Thread.sleep(2000)
            Assert.assertEquals(DataState.Loading, states[0])
            Assert.assertEquals(DataState.Error, states[1])
        }
    }
