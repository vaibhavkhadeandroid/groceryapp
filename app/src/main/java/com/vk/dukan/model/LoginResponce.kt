package com.vk.dukan.model

data class LoginResponce(
    val authenticationtoken: String,
    val errormessage: String,
    val status: String,
    val userDetails: UserDetails
)