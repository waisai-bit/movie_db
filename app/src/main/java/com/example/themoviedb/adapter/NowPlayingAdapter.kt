package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.now_playing_item.view.*
import com.example.themoviedb.model.Result

class NowPlayingAdapter(var nowPlayingList: List<Result> = ArrayList()) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    private var clickListener: ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var nowPlayingList: Result
        fun bindNowPlaying(nowPlaying: Result) {
            this.nowPlayingList = nowPlaying
            var baseUrl = "https://image.tmdb.org/t/p/w500/"
            Picasso.get()
                .load(baseUrl+nowPlaying.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.nowPlayingImage)
            itemView.nowPlayingTitle.text = nowPlaying.title
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(nowPlayingList)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.now_playing_item, parent, false)
        return NowPlayingViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = nowPlayingList.size

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bindNowPlaying(nowPlayingList[position])
    }

    fun updateNowPlaying(nowPlayingList: List<Result>) {
        this.nowPlayingList = nowPlayingList
        notifyDataSetChanged()
    }

}

interface ClickListener {
    fun onClick(nowPlayingList: Result)
}