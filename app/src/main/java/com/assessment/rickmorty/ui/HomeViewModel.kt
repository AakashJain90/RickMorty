package com.assessment.rickmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.data.model.CharactersInternalResponse
import com.assessment.rickmorty.data.model.LocationInternalResponse
import com.assessment.rickmorty.data.repository.IDataRepository
import com.assessment.rickmorty.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IDataRepository) : BaseViewModel() {

    // livedata for character listing.
    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    private val _characterSelected = MutableLiveData<Character>()
    val characterSelected: LiveData<Character> = _characterSelected

    init {
        viewModelScope.launch(Dispatchers.IO) {

            isLoading.postValue(true)
            val response = repository.getCharacters()
            isLoading.postValue(false)

            when (response) {
                is CharactersInternalResponse.Fail -> toastMsg.postValue(response.errorMsg)
                is CharactersInternalResponse.Success -> _characterList.postValue(response.listCharacters)
            }
        }
    }

    fun characterSelected(character: Character) {
        _characterSelected.value = character

        viewModelScope.launch(Dispatchers.IO) {

            when (val locationInternalResponse = repository.getLocationByUrl(character.location.url)) {
                is LocationInternalResponse.Fail -> toastMsg.postValue(locationInternalResponse.errorMsg)
                is LocationInternalResponse.Success -> {
                    _characterSelected.value?.location = locationInternalResponse.location
                    _characterSelected.postValue(_characterSelected.value)
                }
            }
        }
    }
}