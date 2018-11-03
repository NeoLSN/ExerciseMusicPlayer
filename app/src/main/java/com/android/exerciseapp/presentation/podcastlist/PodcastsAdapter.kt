package com.android.exerciseapp.presentation.podcastlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.exerciseapp.BR
import com.android.exerciseapp.R
import com.android.exerciseapp.util.recyclerview.viewholder.BaseViewHolder
import java.util.*

/**
 * Created by JasonYang.
 */
class PodcastsAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var items: List<String> = Collections.emptyList()
        set(newItems) {
            val cb = AdapterDiffCallback(field, newItems)
            val result = DiffUtil.calculateDiff(cb, false)
            field = newItems
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.podcast_item, parent, false)
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val place = items[position]
        holder.bindVariable(BR.podcast, place)
    }
}