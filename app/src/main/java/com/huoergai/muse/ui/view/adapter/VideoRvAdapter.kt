package com.huoergai.muse.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseRecyclerViewAdapter
import com.huoergai.muse.network.model.network.Video
import com.huoergai.muse.ui.view.viewholder.VideoViewHolder

/**
 * D&T: 2023-10-25 10:41
 * DES:
 */
class VideoRvAdapter : BaseRecyclerViewAdapter<VideoViewHolder>() {
    private val videos: MutableList<Video> = mutableListOf()
    private var itemClickListener: View.OnClickListener? = null

    fun setItemClickListener(listener: View.OnClickListener) {
        this.itemClickListener = listener
    }

    fun setData(list: List<Video>) {
        videos.clear()
        videos.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Video>) {
        val start = videos.size
        videos.addAll(list)
        notifyItemRangeChanged(start, list.size)
    }

    fun getData(pos: Int): Video {
        return videos[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        view.setOnClickListener(itemClickListener)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }
}