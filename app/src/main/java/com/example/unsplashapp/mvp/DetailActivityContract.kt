package com.example.unsplashapp.mvp

import com.example.unsplashapp.POJO.MyData

interface DetailActivityContract {
    interface View{
        fun showData(data: MyData)
        fun showError(message: String)
        fun showProgressbar()
        fun hideProgressbar()
        fun showComplate()
        fun makeAllVisible()
    }

    interface Presenter{
        fun loadData(id: String)
    }
}