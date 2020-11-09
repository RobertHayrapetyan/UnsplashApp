package com.example.unsplashapp.mvp


import android.util.Log
import com.example.unsplashapp.POJO.MyData
import com.example.unsplashapp.retrofit.APIInterface
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenterImpl @Inject constructor(val apiInterface: APIInterface, val view: DetailActivityContract.View):
    DetailActivityContract.Presenter {
    private val TAG = "TEST"

    private var lastPage: Int? = null

    override fun loadData(id: String) {
        view.showProgressbar()
        Log.d(TAG, "loadData: $id")
        apiInterface.getPhotoDetail("$id","qxf4hVc_ON0_szOMNL2wn24LsByGzLbCNXi4uLzl4VU")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MyData> {
                override fun onCompleted() {
                    view.showComplate()
                    view.hideProgressbar()
                }

                override fun onNext(t: MyData?) {
                    Log.d(TAG, "onNext: ${t!!.id}")
                    view.showData(t)
                }

                override fun onError(e: Throwable?) {
                    view.showError(e?.message.toString())
                    view.hideProgressbar()
                }
            })
    }

}