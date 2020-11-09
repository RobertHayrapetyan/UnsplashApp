package com.example.unsplashapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplashapp.POJO.FindPhotos
import com.example.unsplashapp.POJO.MyData
import com.example.unsplashapp.di.components.ApplicationComponent
import com.example.unsplashapp.di.components.DaggerMainActivityComponent
import com.example.unsplashapp.di.components.MainActivityComponent
import com.example.unsplashapp.di.modules.MainActivityMvpModule
import com.example.unsplashapp.mvp.MainActivityContract
import com.example.unsplashapp.mvp.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View,
    RecyclerViewAdapter.ClickListener {

    private val TAG = "TEST"

    val progressBar by lazy { progress_bar }
    val recyclerView by lazy { recycler_view }

    lateinit var mainActivityComponent: MainActivityComponent

    @Inject
    lateinit var lruCache: LruCache<String, Bitmap>

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    @Inject
    lateinit var mainPresenter: MainPresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationComponent: ApplicationComponent =
            MyApplication().get(this).getApplicationComponent()

        mainActivityComponent = DaggerMainActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .mainActivityMvpModule(MainActivityMvpModule(this))
            .build()

        mainActivityComponent.injectMainActivity(this)

        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewAdapter.clickListener = object : RecyclerViewAdapter.ClickListener {
            override fun launchIntent(id: String) {
                mainPresenter.onItemClick(id)
            }

        }

        recyclerView.adapter = recyclerViewAdapter
        mainPresenter.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.search_view)

        val searchView = searchItem?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isEmpty()){
                    mainPresenter.loadData()
                }else{
                    mainPresenter.searchData(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()){
                    mainPresenter.loadData()
                }else{
                    mainPresenter.searchData(newText)
                }
                return false
            }
        })
        return true
    }


    override fun showData(data: List<MyData>) {
        recyclerViewAdapter.setData(data)
    }

    override fun showFindData(data: FindPhotos) {

        recyclerViewAdapter.setData(data.results)

    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, "showError: $message")
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE;
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE;
    }

    override fun showComplate() {
    }

    override fun launchIntent(id: String) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra("id", id))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

}