package com.example.unsplashapp.di.components

import com.example.unsplashapp.MainActivity
import com.example.unsplashapp.di.modules.MainActivityMvpModule
import com.example.unsplashapp.di.modules.AdapterModule
import com.example.unsplashapp.di.modules.LruCacheModule
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [AdapterModule::class, MainActivityMvpModule::class, LruCacheModule::class],
dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}