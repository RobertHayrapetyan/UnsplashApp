package com.example.unsplashapp.retrofit

import com.example.unsplashapp.POJO.FindPhotos
import com.example.unsplashapp.POJO.MyData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface APIInterface {

    @GET("/photos")
    fun getData(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<List<MyData>>

    @GET("/search/photos")
    fun getSearchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") criteri: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<FindPhotos>

    @GET("/photos/{id}")
    fun getPhotoDetail(
        @Path("id") photoId: String,
        @Query("client_id") clientId: String
    ): Observable<MyData>
}