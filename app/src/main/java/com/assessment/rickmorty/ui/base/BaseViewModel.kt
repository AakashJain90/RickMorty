package com.assessment.rickmorty.ui.base

import androidx.lifecycle.ViewModel
import com.assessment.rickmorty.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val toastMsg: SingleLiveEvent<String> = SingleLiveEvent()
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

}