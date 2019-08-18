package com.example.netbenefitsapp.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Video
import com.example.netbenefitsapp.view.activities.webview.LibraryWebViewActivity
import kotlinx.android.synthetic.main.video_item.view.*

class VideoAdapter(private val videos : ArrayList<Video>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false))

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setValues(videos[position])

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var mVideo : Video

        fun setValues(video: Video) {
            itemView.tvVideoTitle.text = video.title
            itemView.tvVideoDescription.text = video.description
            itemView.setOnClickListener(this)
            this.mVideo = video
        }

        override fun onClick(view: View?) {
            val intent = Intent(view?.context, LibraryWebViewActivity::class.java)
            intent.putExtra("libraryUrl", mVideo.url)
            view?.context?.startActivity(intent)
        }

    }
}