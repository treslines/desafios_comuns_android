package com.progdeelite.dca.endpoints

object RequestBody {

    data class LoginBody(
        val username: String,
        val password: String
    )
}