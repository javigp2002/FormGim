package com.appgim.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GimApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}