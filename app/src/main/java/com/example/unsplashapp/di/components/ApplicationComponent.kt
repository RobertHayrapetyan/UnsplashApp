package com.example.unsplashapp.di.components

import com.example.unsplashapp.retrofit.APIInterface
import com.example.unsplashapp.di.scopes.ApplicationScope
import com.example.unsplashapp.MyApplication
import com.example.unsplashapp.di.modules.RetrofitModule
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {
    fun getApiInterface(): APIInterface
    fun injectApplication(myApplication: MyApplication)
}