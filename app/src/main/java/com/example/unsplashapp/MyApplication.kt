package com.example.unsplashapp

import android.app.Activity
import android.app.Application
import com.example.unsplashapp.di.components.ApplicationComponent
import com.example.unsplashapp.di.components.DaggerApplicationComponent

class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .build()
        appComponent.injectApplication(this)
    }

    fun get(activity: Activity):MyApplication{
        return activity.application as MyApplication
    }

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }
}
