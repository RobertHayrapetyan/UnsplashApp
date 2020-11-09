package com.example.unsplashapp.mvp

import com.example.unsplashapp.POJO.FindPhotos
import com.example.unsplashapp.POJO.MyData

interface MainActivityContract {
    interface View{
        fun showData(data: List<MyData>)
        fun showFindData(data: FindPhotos)
        fun showError(message: String)
        fun showProgressbar()
        fun hideProgressbar()
        fun showComplate()
    }

    interface Presenter{
        fun loadData()
        fun searchData(criteria: String)
        fun onItemClick(id: String)
    }
}