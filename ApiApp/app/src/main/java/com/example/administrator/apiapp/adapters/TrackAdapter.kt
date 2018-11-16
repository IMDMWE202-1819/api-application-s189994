package com.example.administrator.apiapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.models.TrackData

//making the Adapter class for the Album page, which will fit the album data into the layout objects.
class TrackAdapter (private val data:List<TrackData>,private val context: Context, val onTrackClick: (track: TrackData) -> Unit ) : RecyclerView.Adapter<TrackAdapter.TrackHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackAdapter.TrackHolder { //creating a viewHolder because the Adapter needs an implementation of the original abstract class
        val trackView = LayoutInflater.from(context).inflate(R.layout.track_list_item, parent, false) //making an inflater for usage of the layout for listing item
        return TrackHolder(trackView)
    }

    override fun getItemCount(): Int {
        return data.size //returns the number of current items available in the Adapter
    }

    override fun onBindViewHolder(holder: TrackAdapter.TrackHolder, position: Int) {
        holder.bindTrack(data[position])  //using to cache the view objects for saving memory (whenever scrolled, the disappeared data goes on pause and that way the app is not going to be slow as its not going to be overwhelmed
    }

    inner class TrackHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val trackName = itemView.findViewById<TextView>(R.id.track_list_textView)

        fun bindTrack(track: TrackData?) {
            if (track == null) return //if tracks none - return nothing

            if (trackName != null)
                trackName.text = "${track.track_position}. ${track.title}" // showing the requested data


            itemView.setOnClickListener { onTrackClick(track) }
        }

    }
}