package com.example.unsplashapp.di.modules

import android.content.Context
import com.example.unsplashapp.MainActivity
import com.example.unsplashapp.di.qualifiers.ActivityContext
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityContextModule (val mainActivity: MainActivity){

    val context: Context by lazy { mainActivity }

    @Provides
    @ActivityScope
    fun providesMainActivity(): MainActivity {
        return mainActivity
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun providesContext(): Context{
        return context
    }
}