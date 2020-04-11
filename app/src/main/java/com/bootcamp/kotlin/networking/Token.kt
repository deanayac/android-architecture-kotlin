package com.bootcamp.kotlin.networking

import com.google.gson.annotations.SerializedName

data class Token(
    var success: Boolean,
    @SerializedName("expires_at") var expiresAt: String,
    @SerializedName("request_token") var requestToken: String
)