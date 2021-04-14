package com.xixilala.mvidemo.data.model

import com.squareup.moshi.Json

data class Users(
    @Json(name="data")
    val data:List<User> = ArrayList()
)