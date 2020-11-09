package com.example.unsplashapp.POJO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindPhotos(
    val results: List<MyData>
) : Parcelable