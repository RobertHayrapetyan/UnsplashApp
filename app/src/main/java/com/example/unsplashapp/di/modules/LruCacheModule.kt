package com.example.unsplashapp.di.modules

import android.graphics.Bitmap
import android.util.LruCache
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [MainActivityContextModule::class])
class LruCacheModule {
    val maxSize = ((Runtime.getRuntime().maxMemory() / 1024) / 8).toInt()

    @ActivityScope
    @Provides
    fun providesLruCache(): LruCache<String, Bitmap>{
        return LruCache<String, Bitmap>(maxSize)
    }
}