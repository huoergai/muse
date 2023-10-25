package com.huoergai.muse.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.model.network.Review
import com.huoergai.muse.ui.view.viewholder.ReviewViewHolder

/**
 * D&T: 2023-10-25 11:12
 * DES:
 */
class ReviewRvAdapter : BaseRecyclerViewAdapter<ReviewViewHolder>() {
    private val reviews: MutableList<Review> = mutableListOf()
    private var itemClickListener: View.OnClickListener? = null

    fun setItemClickListener(listener: View.OnClickListener) {
        this.itemClickListener = listener
    }

    fun setData(list: List<Review>) {
        reviews.clear()
        reviews.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Review>) {
        val start = reviews.size
        reviews.addAll(list)
        notifyItemRangeChanged(start, list.size)
    }

    fun getData(pos: Int): Review {
        return reviews[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        view.setOnClickListener(itemClickListener)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }
}