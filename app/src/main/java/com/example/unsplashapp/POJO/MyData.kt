package com.example.unsplashapp.POJO

import android.os.Parcelable
import com.example.unsplashapp.POJO.DataURL
import com.example.unsplashapp.POJO.DataUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyData(
    val id: String,
    val created_at: String,
    val alt_description: String?,
    val description: String?,
    val urls: DataURL,
    val user: DataUser
) : Parcelable
