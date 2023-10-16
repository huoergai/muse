package com.huoergai.muse.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.base.GlobalDataStore
import com.huoergai.muse.model.entity.Movie

/**
 * D&T: 2023-10-12 20:04
 * DES:
 */
class RvAdapter : BaseRecyclerViewAdapter<MovieRvHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieRvHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieRvHolder, position: Int) {
        holder.bind(movies[position])
    }

}

class MovieRvHolder(itemView: View) : BaseViewHolder(itemView) {
    val ivPoster = itemView.findViewById<AppCompatImageView>(R.id.iv_poster)
    val mtvTitle = itemView.findViewById<MaterialTextView>(R.id.mtv_title)
    val ratingBar = itemView.findViewById<RatingBar>(R.id.rating_bar)

    fun bind(movie: Movie) {
        mtvTitle.text = movie.title
        ratingBar.rating = movie.vote_average / 2f

        Glide.with(ivPoster.context)
            .load(GlobalDataStore.buildImageUrl(movie.poster_path))
            .into(ivPoster)
    }

}