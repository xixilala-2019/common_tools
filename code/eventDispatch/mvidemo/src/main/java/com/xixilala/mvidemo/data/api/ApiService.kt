package com.xixilala.mvidemo.data.api

import com.xixilala.mvidemo.data.model.User
import com.xixilala.mvidemo.data.model.Users
import retrofit2.http.GET

interface  ApiService {
    @GET("users")
    suspend fun getUsers(): Users
}