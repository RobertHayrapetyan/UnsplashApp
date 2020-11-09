package com.example.unsplashapp.POJO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataUser(
    val username:String,
    val first_name:String,
    val last_name:String,
    val profile_image: DataUserLinks
):Parcelable