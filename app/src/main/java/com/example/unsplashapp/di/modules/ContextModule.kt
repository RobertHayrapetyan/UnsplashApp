package com.example.unsplashapp.di.modules

import android.content.Context
import com.example.unsplashapp.di.qualifiers.ApplicationContext
import com.example.unsplashapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    @ApplicationScope
    @ApplicationContext
    fun providesContext(): Context{
        return context
    }
}