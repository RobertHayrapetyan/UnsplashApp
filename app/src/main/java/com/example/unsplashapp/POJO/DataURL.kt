package com.example.unsplashapp.POJO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataURL(
    val regular: String,
    val thumb: String
):Parcelable
