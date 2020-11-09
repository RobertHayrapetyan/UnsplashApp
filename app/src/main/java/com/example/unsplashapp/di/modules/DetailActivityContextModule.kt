package com.example.unsplashapp.di.modules

import android.content.Context
import com.example.unsplashapp.DetailActivity
import com.example.unsplashapp.MainActivity
import com.example.unsplashapp.di.qualifiers.ActivityContext
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class DetailActivityContextModule (val detalActivity: DetailActivity){

    val context: Context by lazy { detalActivity }

    @Provides
    @ActivityScope
    fun providesMainActivity(): DetailActivity {
        return detalActivity
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun providesContext(): Context{
        return context
    }
}