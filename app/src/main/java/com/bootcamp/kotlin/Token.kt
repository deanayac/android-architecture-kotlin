package com.bootcamp.kotlin

data class Token(
    var success:Boolean,
    var expires_at : String,
    var request_token :String
)