package com.farm2seoul_frontend_aos.data.repository

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Farm2SeoulApplication : Application() {
    companion object {
        lateinit var preference: SharedPreference
    }

    override fun onCreate() {
        preference = SharedPreference(applicationContext)
        super.onCreate()
    }
}