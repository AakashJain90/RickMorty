package com.assessment.rickmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assessment.rickmorty.repositories.FakeCharactersRepository
import com.assessment.rickmorty.ui.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_viewmodel_with_network_error_response() {
        val repository = FakeCharactersRepository(true)

        val viewModel = HomeViewModel(repository)

        val msgResponse = viewModel.toastMsg.getOrAwaitValueTest()

        assert(msgResponse.equals(repository.networkError))
    }

    @Test
    fun test_viewmodel_with_valid_network_response() {
        val repository = FakeCharactersRepository()

        val viewModel = HomeViewModel(repository)

        val characterListResp = viewModel.characterList.getOrAwaitValueTest()

        assert(characterListResp.size == repository.characterList.size)

        // each element contains in original data set inside the repository.
        for (character in characterListResp) {
            assert(repository.characterList.contains(character))
        }
    }

}