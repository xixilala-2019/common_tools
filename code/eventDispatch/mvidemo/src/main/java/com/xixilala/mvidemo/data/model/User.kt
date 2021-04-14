package com.xixilala.mvidemo.data.model

import com.squareup.moshi.Json

class User (
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "first_name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
) {

}