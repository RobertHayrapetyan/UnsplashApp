package com.example.unsplashapp.di.modules

import com.example.unsplashapp.mvp.MainActivityContract
import com.example.unsplashapp.di.scopes.ActivityScope
import com.example.unsplashapp.mvp.DetailActivityContract
import dagger.Module
import dagger.Provides

@Module
class DetailActivityMvpModule(val view: DetailActivityContract.View) {
    @Provides
    @ActivityScope
    fun provideView(): DetailActivityContract.View {
        return view
    }
}