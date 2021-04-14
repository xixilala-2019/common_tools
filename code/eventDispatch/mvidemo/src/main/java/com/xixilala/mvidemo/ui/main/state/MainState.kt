package com.xixilala.mvidemo.ui.main.state

import com.xixilala.mvidemo.data.model.User

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}