package com.more9810.photogallery.presentation.adapters

import com.more9810.photogallery.R
import com.more9810.photogallery.domain.model.Photo
import com.more9810.photogallery.presentation.baseAdapter.BaseRecyclerViewAdapter

class PhotoRecyclerAdapter(val items: List<Photo>): BaseRecyclerViewAdapter<Photo>(items, null) {
    override val layoutId = R.layout.photo_item
    override fun isSame(oItem: Photo, nItem: Photo): Boolean =
        oItem.id == nItem.id

    override fun isSameContent(oItem: Photo, nItem: Photo): Boolean =
        oItem == nItem
    override fun getItemId(position: Int) = items[position].id.hashCode().toLong()

}