package com.example.unsplashapp.mvp


import com.example.unsplashapp.POJO.FindPhotos
import com.example.unsplashapp.POJO.MyData
import com.example.unsplashapp.retrofit.APIInterface
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(val apiInterface: APIInterface, val view: MainActivityContract.View):
    MainActivityContract.Presenter {

    private var lastPage: Int? = null

    override fun loadData() {
        var page = 1
        view.showProgressbar()
        apiInterface.getData("qxf4hVc_ON0_szOMNL2wn24LsByGzLbCNXi4uLzl4VU", page, 100)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MyData>> {
                override fun onCompleted() {
                    view.showComplate()
                    view.hideProgressbar()
                }

                override fun onNext(t: List<MyData>?) {
                    view.showData(t!!)
                }

                override fun onError(e: Throwable?) {
                    view.showError(e?.message.toString())
                    view.hideProgressbar()
                }
            })
    }

    override fun searchData(criteria: String) {
        var page = 1
        view.showProgressbar()
        apiInterface.getSearchPhotos("qxf4hVc_ON0_szOMNL2wn24LsByGzLbCNXi4uLzl4VU", criteria, page, 100 )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<FindPhotos>{
                override fun onCompleted() {
                    view.showComplate()
                    view.hideProgressbar()
                }

                override fun onError(e: Throwable?) {
                    view.showError(e?.message.toString())
                    view.hideProgressbar()
                }

                override fun onNext(t: FindPhotos?) {
                    view.showFindData(t!!)
                }

            })
    }

    override fun onItemClick(id:String) {

    }
}