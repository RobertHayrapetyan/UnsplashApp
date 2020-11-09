package com.example.unsplashapp.di.modules

import com.example.unsplashapp.mvp.MainActivityContract
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityMvpModule(val view: MainActivityContract.View) {
    @Provides
    @ActivityScope
    fun provideView(): MainActivityContract.View {
        return view
    }
}