package com.benten.creaturesapp.views.allCreatures

import com.benten.creaturesapp.CreaturesRepoImplFake
import com.benten.creaturesapp.model.CreaturesRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class AllCreaturesViewModelTest {
    

    private lateinit var viewModel: AllCreaturesViewModel

    @Before
    fun setUpTests() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = AllCreaturesViewModel(CreaturesRepoImplFake())
    }

    @Test
    fun `test_view_model_simple`() {
        assertThat(viewModel.uiState.value).isNotNull()
    }

    @Test
    fun `test_search_works_correctly`() {
        viewModel.onSearchRequested("some string")
        assertThat(viewModel.uiState.value).isInstanceOf(AllCreaturesState.Success::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}