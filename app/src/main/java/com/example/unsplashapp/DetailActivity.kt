package com.example.unsplashapp

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unsplashapp.POJO.MyData
import com.example.unsplashapp.di.components.ApplicationComponent
import com.example.unsplashapp.di.components.DaggerDetailActivityComponent
import com.example.unsplashapp.di.components.DetailActivityComponent
import com.example.unsplashapp.di.modules.DetailActivityMvpModule
import com.example.unsplashapp.mvp.DetailActivityContract
import com.example.unsplashapp.mvp.DetailPresenterImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailActivityContract.View{

    private val TAG = "TEST"

    @Inject
    lateinit var detailPresenterImpl: DetailPresenterImpl

    @Inject
    lateinit var lruCache: LruCache<String, Bitmap>

    lateinit var detailActivityComponent: DetailActivityComponent

    val progressBar by lazy { progress_bar }
    val image by lazy { detail_image }
    val imageTitle by lazy { detail_title }
    val createdAt by lazy { detail_created_at }
    val imageDescription by lazy { description }
    val authorIcon by lazy {detail_owner_icon_view }
    val authorName by lazy { detail_full_name }
    val authorUsername by lazy { detail_username }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val applicationComponent: ApplicationComponent =
            MyApplication().get(this).getApplicationComponent()

        detailActivityComponent = DaggerDetailActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .detailActivityMvpModule(DetailActivityMvpModule(this))
            .build()

        detailActivityComponent.injectDetailActivity(this)

        val id = intent.getStringExtra("photoId")
        Log.d(TAG, "onCreate: $id")
        detailPresenterImpl.loadData(id!!)


    }

    override fun showData(data: MyData) {

        val bitmap = MyLruCache(lruCache).getBitmapFromCache(data.urls.regular)
        Log.d(TAG, "showData2: ${bitmap}")
        //Picasso.get().load(data.urls.regular).into(this.image)
        image.setImageBitmap(bitmap)
        imageTitle.text = "Title:\n \"${data.alt_description}\""
        createdAt.text = "Created at:\n \"${data.created_at}\""
        imageDescription.text = "Description:\n \"${data.description}\""
        Picasso.get().load(data.user.profile_image.medium).into(this.authorIcon)
        authorName.text = "${data.user.first_name} ${data.user.last_name}"
        authorUsername.text = data.user.username
        makeAllVisible()

    }

     override fun makeAllVisible() {
        image.visibility = View.VISIBLE
        if(imageTitle.text.toString() != "Title:\n \"null\"") imageTitle.visibility = View.VISIBLE
        if(imageDescription.text.toString() != "Description:\n \"null\"") imageDescription.visibility = View.VISIBLE
        createdAt.visibility = View.VISIBLE
        authorIcon.visibility = View.VISIBLE
        authorName.visibility = View.VISIBLE
        authorUsername.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, "showError: $message")
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun showComplate() {

    }
}