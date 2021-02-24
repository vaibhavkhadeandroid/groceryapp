package com.vk.dukan.network

import com.vk.dukan.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("/login")
    fun getLogin( @Body login : Login): Call<LoginResponce>
    @POST("/authenticate")
     fun getAuthenticationToken(@Body authentication: Authentication):Call<AuthenticationResult>
    @POST("/register")
     fun getRegistrationService(registation: RegistationPojo): Call<RegistationResponce>

}