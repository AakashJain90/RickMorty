package com.assessment.rickmorty

import android.content.Context
import com.assessment.rickmorty.data.model.CharactersInternalResponse
import com.assessment.rickmorty.data.model.CharactersServiceResponse
import com.assessment.rickmorty.data.network.APIService
import com.assessment.rickmorty.data.repository.DataRepository
import com.assessment.rickmorty.utils.FakeCharacters
import com.assessment.rickmorty.utils.isNetworkAvailable
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    private lateinit var repository: DataRepository

    private val networkUnavailable = "Network Unavailable"
    private val networkError = "Network Error Occurred"

    private val context = mockk<Context> {
        coEvery { getString(R.string.network_unavail) } returns networkUnavailable
    }
    private val service = mockk<APIService>()

    @Before
    fun setup() {
        repository = DataRepository(context, service)
    }

    @Test
    fun test_get_characters_with_network_unavailable_should_return_fail_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns false

        val response = repository.getCharacters()

        assert(response is CharactersInternalResponse.Fail)
    }

    @Test
    fun test_get_characters_with_network_unavailable_should_return_error_msg() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns false

        val response = repository.getCharacters()

        assert((response as CharactersInternalResponse.Fail).errorMsg == networkUnavailable)
    }

    @Test
    fun test_get_characters_with_network_error_should_return_fail_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val resp: Response<CharactersServiceResponse> = mockk {
            coEvery { isSuccessful } returns false
            coEvery { message() } returns networkError
        }
        coEvery { service.getCharacters() } returns resp

        val response = repository.getCharacters()

        assert(response is CharactersInternalResponse.Fail)
        assert((response as CharactersInternalResponse.Fail).errorMsg == networkError)
    }

    @Test
    fun test_get_characters_with_valid_network_should_return_Success() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val characterServiceResp: CharactersServiceResponse = mockk {
            coEvery { results } returns listOf()
        }
        val resp: Response<CharactersServiceResponse> = mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns characterServiceResp
        }
        coEvery { service.getCharacters() } returns resp

        val response = repository.getCharacters()

        assert(response is CharactersInternalResponse.Success)
    }

    @Test
    fun test_get_characters_with_valid_network_should_return_correct_characters_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val characterServiceResp: CharactersServiceResponse = mockk {
            coEvery { results } returns FakeCharacters.characterList
        }
        val resp: Response<CharactersServiceResponse> = mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns characterServiceResp
        }
        coEvery { service.getCharacters() } returns resp

        val response = repository.getCharacters()

        val listCharactersResp = (response as CharactersInternalResponse.Success).listCharacters
        assert(listCharactersResp.size == FakeCharacters.characterList.size)

        // each element contains in original data set.
        for (character in listCharactersResp) {
            assert(FakeCharacters.characterList.contains(character))
        }
    }
}