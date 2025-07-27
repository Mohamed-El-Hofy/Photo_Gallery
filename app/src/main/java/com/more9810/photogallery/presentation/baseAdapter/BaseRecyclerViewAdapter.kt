package com.more9810.photogallery.presentation.baseAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.more9810.photogallery.BR

interface BaseInterActionListener
abstract class BaseRecyclerViewAdapter<T>(
    private var item: List<T>,
    private val listener: BaseInterActionListener?,
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>() {
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutId, parent, false

            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = item[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.item, currentItem)
                holder.binding.executePendingBindings()

            }

            else -> throw Exception("in Base RecyclerView not Same Holder")
        }


    }

    override fun getItemCount() = item.size
    fun getItem() = item
    fun setItem(newItem: List<T>) {
            val myDiffer = MyDiffer(item, newItem, ::isSame, ::isSameContent)
            val differ = DiffUtil.calculateDiff(myDiffer)
            item = newItem
            differ.dispatchUpdatesTo(this)

    }

    open fun isSame(oItem: T, nItem: T): Boolean {
        return oItem?.equals(nItem) == true
    }

    open fun isSameContent(oItem: T, nItem: T): Boolean {
        return oItem?.equals(nItem) == true
    }


    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    abstract class BaseViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}