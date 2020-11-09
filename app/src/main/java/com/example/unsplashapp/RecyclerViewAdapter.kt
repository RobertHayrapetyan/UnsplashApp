package com.example.unsplashapp

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashapp.POJO.MyData
import com.example.unsplashapp.RecyclerViewAdapter.ViewHolder
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import com.example.unsplashapp.MyLruCache


class RecyclerViewAdapter @Inject constructor(val lruCache: LruCache<String, Bitmap>) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {


    private val TAG = "Test"
    val data: MutableList<MyData>
    var filterData = ArrayList<MyData>()
    var clickListener: ClickListener? = null


    init {
        data = filterData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitmap = MyLruCache(lruCache).getBitmapFromCache(filterData[position].urls.thumb)
        Log.d(TAG, "binding: ${lruCache.size()}")
        holder.itemImageView.setImageBitmap(bitmap)
        Picasso.get().load(filterData[position].user.profile_image.medium).into(holder.userIcon)
        holder.ownerName.text = filterData[position].user.first_name
        holder.ownerLastName.text = filterData[position].user.last_name
        holder.ownerUsername.text = filterData[position].user.username

    }

    override fun getItemCount(): Int {
        return filterData.size
    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageView: ImageView
        val userIcon: CircleImageView
        val ownerName: TextView
        val ownerLastName: TextView
        val ownerUsername: TextView
        val constraintLayoutContainer: ConstraintLayout

        init {
            itemImageView = itemView.item_image_view
            userIcon = itemView.owner_icon_view
            ownerName = itemView.first_name
            ownerLastName = itemView.last_name
            ownerUsername = itemView.username
            constraintLayoutContainer = itemView.constraintLayout
            constraintLayoutContainer.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("photoId", filterData[adapterPosition].id)
                    itemView.context.startActivity(intent)
                }
            })
        }
    }

    interface ClickListener {
        fun launchIntent(id: String)
    }

    fun setData(data: List<MyData>?) {
        filterData = data as ArrayList<MyData>
        Log.d(TAG, "setData============================: $data")
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterData = if (charSearch.isEmpty()) {
                    data as ArrayList<MyData>
                } else {
                    val resultList = ArrayList<MyData>()
                    for (item in data) {
                        if (item.alt_description?.toLowerCase(Locale.ROOT)?.contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )!!
                            || item.description?.toLowerCase(Locale.ROOT)?.contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )!!
                        ) {
                            resultList.add(item)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterData
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterData = results?.values as ArrayList<MyData>
                notifyDataSetChanged()
            }

        }
    }
}



