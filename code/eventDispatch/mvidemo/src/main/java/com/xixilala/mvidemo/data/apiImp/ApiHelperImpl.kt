package com.xixilala.mvidemo.data.apiImp

import com.xixilala.mvidemo.data.api.ApiService
import com.xixilala.mvidemo.data.model.User
import com.xixilala.mvidemo.data.model.Users

class ApiHelperImpl(private val apiService: ApiService) : ApiService {
    override suspend fun getUsers(): Users {
        val users = apiService.getUsers()
        return users
    }
}