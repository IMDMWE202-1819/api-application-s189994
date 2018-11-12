package com.example.administrator.apiapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.models.TrackData


class TrackAdapter (private val data:List<TrackData>,private val context: Context, val onTrackClick: (track: TrackData) -> Unit ) : RecyclerView.Adapter<TrackAdapter.TrackHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAdapter.TrackHolder {
        val trackView = LayoutInflater.from(context).inflate(R.layout.track_list_item, parent, false)
        return TrackHolder(trackView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TrackAdapter.TrackHolder, position: Int) {
        holder.bindTrack(data[position])
    }

    inner class TrackHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val trackName = itemView.findViewById<TextView>(R.id.track_list_textView)

        fun bindTrack(track: TrackData?) {
            if (track == null) return

            if (trackName != null)
                trackName.text = "${track.track_position}. ${track.title}"


            itemView.setOnClickListener { onTrackClick(track) }
        }

    }
}