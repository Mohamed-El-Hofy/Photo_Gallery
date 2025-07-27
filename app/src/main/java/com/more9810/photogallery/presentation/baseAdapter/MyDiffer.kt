package com.more9810.photogallery.presentation.baseAdapter

import androidx.recyclerview.widget.DiffUtil

class MyDiffer<T>(
    private val oItem: List<T>,
    private val nItem: List<T>,
    private val isSame: (T, T) -> Boolean,
    private val isSameContent: (T, T) -> Boolean,
): DiffUtil.Callback()  {
    override fun getOldListSize() = oItem.size

    override fun getNewListSize() = nItem.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return isSame(oItem[oldItemPosition],nItem[newItemPosition])
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return isSameContent(oItem[oldItemPosition],nItem[newItemPosition])
    }

}