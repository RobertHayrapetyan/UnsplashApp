package com.example.unsplashapp.di.modules

import android.graphics.Bitmap
import android.util.LruCache
import com.example.unsplashapp.RecyclerViewAdapter
import com.example.unsplashapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module(includes = [MainActivityContextModule::class, LruCacheModule::class])
class AdapterModule {

    val maxSize = ((Runtime.getRuntime().maxMemory() / 1024) / 8).toInt()

    @Provides
    @ActivityScope
    fun getPhotoList(): RecyclerViewAdapter {
        return RecyclerViewAdapter(LruCache<String, Bitmap>(maxSize))
    }
}