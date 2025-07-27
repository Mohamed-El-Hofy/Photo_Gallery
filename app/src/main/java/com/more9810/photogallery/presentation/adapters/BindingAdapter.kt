package com.more9810.photogallery.presentation.adapters

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.more9810.photogallery.R
import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.presentation.baseAdapter.BaseRecyclerViewAdapter

@BindingAdapter(value = ["app:setImageByUrl"])
fun ImageView.setImageByUrl(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter(value = ["app:setItemToRecycler"])
fun <T> RecyclerView.setItemToRecycler(item: List<T>?) {
    if (item != null) {
        (this.adapter as BaseRecyclerViewAdapter<T>?)?.setItem(item)
    } else {
        (this.adapter as BaseRecyclerViewAdapter<T>?)?.setItem(emptyList())
    }
}

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, state: Resource<T>?) {
    if (state is Resource.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE

    }
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: Resource<T>?) {
    if (state is Resource.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE

    }
}

@BindingAdapter("app:showWhenError")
fun <T> View.showWhenError( state: Resource<T>?) {
    if (state is Resource.Error) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE

    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:setNetState")
 fun View.setNetState(state: Boolean){
    if (state){
        (this as TextView).apply {
            setTextColor(ContextCompat.getColor(this.context, R.color.green))
            text = "Connect ✔"
        }
        this.visibility = View.VISIBLE
        this.postDelayed({
            this.visibility = View.GONE
        }, 1500)

    }else{
        (this as TextView).apply {
            setTextColor(ContextCompat.getColor(this.context, R.color.red))
            text = "Disconnect !!"
        }

        this.visibility = View.VISIBLE
    }
}