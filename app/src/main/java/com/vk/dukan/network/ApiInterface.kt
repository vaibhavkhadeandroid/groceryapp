package com.vk.dukan.network

import com.vk.dukan.model.Authentication
import com.vk.dukan.model.AuthenticationResult
import com.vk.dukan.model.Login
import com.vk.dukan.model.LoginResponce
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("/login")
    fun getLogin( @Body login : Login): Call<LoginResponce>
    @POST("/authenticate")
     fun getAuthenticationToken(@Body authentication: Authentication):Call<AuthenticationResult>

}