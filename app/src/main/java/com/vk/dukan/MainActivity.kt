package com.vk.dukan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vk.dukan.database.SP_Constant
import com.vk.dukan.model.Login
import com.vk.dukan.model.LoginResponce
import com.vk.dukan.network.ApiClient
import com.vk.dukan.network.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var login: Login
    var deviceId: String = ""

    var REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getPreferences(MODE_PRIVATE)
        val sharedPrefedit = getPreferences(MODE_PRIVATE).edit()
//        if (!sharedPref.getBoolean(SP_Constant.islogin.toString(), false)) {
//            startActivity(Intent(this, Registation::class.java))
//            finish()
//        }
        getDeviceInfo()
        var login = Login("Android", password.text.toString(), username.text.toString(),
            Build.MODEL,
            Build.MANUFACTURER,
            Build.BRAND,
            Build.VERSION.SDK,
            Build.VERSION.RELEASE,
            deviceId);



        loginbutton.setOnClickListener(View.OnClickListener {
            val request = ApiClient.buildService(ApiInterface::class.java)
            val call = request.getLogin(login)
            call.enqueue(object : Callback<LoginResponce> {
                override fun onResponse(call: Call<LoginResponce>, response: Response<LoginResponce>) {
                    if (response.isSuccessful) {
                        if (response.body()?.status == "sucess") {
                            if (response.body()?.authenticationtoken != null) {
                                Log.e("working....", "" + response.body()?.status.toString())
                                sharedPrefedit.putString(SP_Constant.authentication.toString(), response.body()?.authenticationtoken)
                                sharedPrefedit.putBoolean(SP_Constant.islogin.toString(), true)
                                sharedPrefedit.apply()
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                    Log.e("failed", "failed")
                }
            })
        })
    }

    private fun getDeviceInfo() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions({ Manifest.permission.READ_PHONE_STATE }, 1);
//            } else {
//                // else for if they have already given permission
//            }
//        }
//        val TelephonyMgr = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val imei: String = TelephonyMgr.getImei()
//        } else {
//            val imei: String = TelephonyMgr.getDeviceId()
//        }
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                // performAction(...)


                deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Settings.Secure.getString(applicationContext.getContentResolver(), Settings.Secure.ANDROID_ID)
                } else {
                    val mTelephony = applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    if (mTelephony.deviceId != null) {
                        mTelephony.deviceId
                    } else {
                        Settings.Secure.getString(applicationContext.getContentResolver(), Settings.Secure.ANDROID_ID) }
                }
                Log.e("iddd", "" + deviceId)

            }
//            shouldShowRequestPermissionRationale(...) -> {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected. In this UI,
//            // include a "cancel" or "no thanks" button that allows the user to
//            // continue using your app without granting the permission.
//            showInContextUI(...)
            //   }

            else -> {
                // You can directly ask for the permission.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_PHONE_STATE),
                        REQUEST_CODE
                    )
                }

            }
        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow

                    deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Settings.Secure.getString(
                            applicationContext.getContentResolver(),
                            Settings.Secure.ANDROID_ID
                        )
                    } else {
                        val mTelephony =
                            applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        if (mTelephony.deviceId != null) {
                            mTelephony.deviceId
                        } else {
                            Settings.Secure.getString(
                                applicationContext.getContentResolver(),
                                Settings.Secure.ANDROID_ID
                            )
                        }
                    }

                    Log.e("iddd", "" + deviceId)
                    // in your app.
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}
