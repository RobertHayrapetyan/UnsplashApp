package com.example.unsplashapp.di.components

import com.example.unsplashapp.DetailActivity
import com.example.unsplashapp.di.modules.DetailActivityMvpModule
import com.example.unsplashapp.di.modules.LruCacheModule
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [DetailActivityMvpModule::class, LruCacheModule::class],
dependencies = [ApplicationComponent::class])
interface DetailActivityComponent {

    fun injectDetailActivity(detailActivity: DetailActivity)

}
