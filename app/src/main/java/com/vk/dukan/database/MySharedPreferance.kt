package com.vk.dukan.database

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

object MySharedPreferance: AppCompatActivity() {







        fun getSharedPreferance() :SharedPreferences {
            return  getPreferences(Context.MODE_PRIVATE)
        }
    fun getSharedPreferanceEdit() :SharedPreferences.Editor {
        return  getPreferences(Context.MODE_PRIVATE).edit()
    }

}