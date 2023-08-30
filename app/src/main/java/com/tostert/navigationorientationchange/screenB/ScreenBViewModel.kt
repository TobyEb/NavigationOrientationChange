package com.tostert.navigationorientationchange.screenB

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenBViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        Log.i("ScreenBViewModel", "$this init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ScreenBViewModel", "$this onCleared")
    }

}
