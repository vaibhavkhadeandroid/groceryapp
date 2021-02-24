package com.vk.dukan.network

import android.util.Log
import com.vk.dukan.database.SP_Constant
import com.vk.dukan.model.Authentication
import com.vk.dukan.model.AuthenticationResult
import com.vk.dukan.model.Login
import com.vk.dukan.model.LoginResponce
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException

class SupportInterceptor: Interceptor, Authenticator {

    var maintoken:String="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYWFhIiwiZXhwIjoxNjE0MDc4ODY0LCJpYXQiOjE2MTQwNjA4NjR9.QEQ4Zz-izkcvvSl-3jU5_iuFwEfWcwdNjSM2ssjH5lvJB8e-PfOerVwrWth6WeCHDcgfELYKhWLrnHxLex-DHA"

    /**
     * Interceptor class for setting of the headers for every request
     *
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request?.newBuilder()

//            ?.addHeader("Content-Type", "application/json")
//            ?.addHeader("Accept", "application/json")
//            ?.build()

            ?.addHeader("Authorization",maintoken)
            ?.build()


        return chain.proceed(request)
    }

    /**
     * Authenticator for when the authToken need to be refresh and updated
     * everytime we get a 401 error code
     */
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        var requestAvailable: Request? = null
        try {

            Log.e("unthoenticate","we get unAuthenticate token")

            var token :String  =   getAuthenticationTocken()

            requestAvailable = response?.request?.newBuilder()



                ?.addHeader("Authorization",token)
                ?.build()
            return requestAvailable
        } catch (ex: Exception) {


        }
        return requestAvailable
    }

    private fun getAuthenticationTocken(): String {
        lateinit var  token:String
        val request = ApiClient.buildService(ApiInterface::class.java)
        var aaa= Authentication("aaaa","bbbb");
        var call = request.getAuthenticationToken(aaa)



        call.enqueue(object: Callback<AuthenticationResult> {
            override fun onResponse(call: Call<AuthenticationResult>, response: retrofit2.Response<AuthenticationResult>) {
                if(response.isSuccessful){
                    token=  response.body()?.token.toString()
                    maintoken=""
maintoken="Bearer "+ token
                    token=maintoken
                }
                else{
                    Log.e("token","error in getting token0")
                    token=  ""
                }
            }

            override fun onFailure(call: Call<AuthenticationResult>, t: Throwable) {
                Log.e("token","error in getting token1")
                token=  ""
            }
        })

        return token
    }

    }

