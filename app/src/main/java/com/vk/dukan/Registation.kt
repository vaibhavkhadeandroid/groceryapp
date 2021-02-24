package com.vk.dukan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.vk.dukan.database.SP_Constant
import com.vk.dukan.model.LoginResponce
import com.vk.dukan.model.RegistationPojo
import com.vk.dukan.model.RegistationResponce
import com.vk.dukan.network.ApiClient
import com.vk.dukan.network.ApiInterface
import kotlinx.android.synthetic.main.activity_registation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)

        registeruser.setOnClickListener(View.OnClickListener {

            val request = ApiClient.buildService(ApiInterface::class.java)
var registationPojo=RegistationPojo("cccc","cccc","vaibhav@gmail.com","9975241243")
            val call = request.getRegistrationService(registationPojo)
            call.enqueue(object : Callback<RegistationResponce> {
                override fun onResponse(
                    call: Call<RegistationResponce>,
                    response: Response<RegistationResponce>
                ) {

                    if (response.isSuccessful) {

                        Log.e("sucesss","success")
//                        if (response.body()?.status == "sucess") {
////                            if (response.body()?.authenticationtoken != null) {
////
////                                Log.e("working....", "" + response.body()?.status.toString())
////                                sharedPrefedit.putString(
////                                    SP_Constant.authentication.toString(),
////                                    response.body()?.authenticationtoken
////                                )
////                                sharedPrefedit.putBoolean(SP_Constant.islogin.toString(), true)
////
////                                sharedPrefedit.apply()
////
////                            }
//
//                        }

                    }else{
                        Log.e("failed", "failed0")

                    }
                }

                override fun onFailure(call: Call<RegistationResponce>, t: Throwable) {
                    Log.e("failed", "failed1")
                }
            })
        })



    }

}