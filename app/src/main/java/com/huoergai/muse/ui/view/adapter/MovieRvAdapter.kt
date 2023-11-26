package com.huoergai.muse.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.persistence.entity.Movie
import com.huoergai.muse.ui.view.viewholder.MovieViewHolder

/**
 * D&T: 2023-10-12 20:04
 * DES:
 */
class MovieRvAdapter : BaseRecyclerViewAdapter<MovieViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()
    private var itemClickListener: View.OnClickListener? = null

    fun setItemClickListener(listener: View.OnClickListener) {
        this.itemClickListener = listener
    }

    fun setData(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Movie>) {
        val start = movies.size
        movies.addAll(list)
        notifyItemRangeChanged(start, list.size)
    }

    fun getData(pos: Int): Movie {
        return movies[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        view.setOnClickListener(itemClickListener)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

}