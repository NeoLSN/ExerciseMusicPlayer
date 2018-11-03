package com.android.exerciseapp.presentation.podcastlist

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by JasonYang.
 */
class AdapterDiffCallback<T>(
        private val oldList: List<T>,
        private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val newer = newList[newItemPosition]
        return old == newer
    }

}