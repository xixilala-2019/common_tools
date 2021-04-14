package com.xixilala.mvidemo.data.repository

import com.xixilala.mvidemo.data.api.ApiService

class MainRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}