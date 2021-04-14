package com.xixilala.mvidemo.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xixilala.mvidemo.data.repository.MainRepository
import com.xixilala.mvidemo.ui.main.intent.MainIntent
import com.xixilala.mvidemo.ui.main.state.MainState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel(){




    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val textNum = MutableLiveData<String>()
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }


    private fun handleIntent() {
        viewModelScope.launch {

            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(repository.getUsers().data)
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }


}