package com.huoergai.muse.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.persistence.entity.Tv
import com.huoergai.muse.ui.view.viewholder.TvViewHolder

/**
 * D&T: 2023-10-25 16:08
 * DES:
 */
class TvRvAdapter : BaseRecyclerViewAdapter<TvViewHolder>() {
    private val tvs: MutableList<Tv> = mutableListOf()
    private var itemClickListener: View.OnClickListener? = null

    fun setItemClickListener(listener: View.OnClickListener) {
        this.itemClickListener = listener
    }

    fun setData(list: List<Tv>) {
        tvs.clear()
        tvs.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Tv>) {
        val start = tvs.size
        tvs.addAll(list)
        notifyItemRangeChanged(start, list.size)
    }

    fun getData(pos: Int): Tv {
        return tvs[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)
        view.setOnClickListener(itemClickListener)
        return TvViewHolder(view)
    }

    override fun getItemCount(): Int = tvs.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvs[position])
    }
}