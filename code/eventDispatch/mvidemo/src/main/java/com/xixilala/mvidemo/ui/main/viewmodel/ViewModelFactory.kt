package com.xixilala.mvidemo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xixilala.mvidemo.data.api.ApiService
import com.xixilala.mvidemo.data.repository.MainRepository

class ViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}