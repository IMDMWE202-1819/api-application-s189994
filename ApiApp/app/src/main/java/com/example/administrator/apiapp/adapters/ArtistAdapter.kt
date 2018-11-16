package com.example.administrator.apiapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.models.ArtistData
import com.squareup.picasso.Picasso

//making the Adapter class for the Artist Search Page
class ArtistAdapter(private val data:List<ArtistData>, private val context:Context, val onArtistClick: (artist:ArtistData) -> Unit ) : RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {  //creating views for data amd replaces them with new data when the past data is no longer available
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAdapter.ArtistHolder { //creating the viewHolder ---
        val artistView = LayoutInflater.from(context).inflate(R.layout.artist_list_item, parent, false) //making an inflater for usage of the layout for listing item
        return ArtistHolder(artistView)
    }

    override fun getItemCount(): Int {
        return data.size //returns the number of current items available in the Album Adapter
    }

    override fun onBindViewHolder(holder: ArtistAdapter.ArtistHolder, position: Int) {
        holder.bindArtist(data[position]) //using to cache the view objects for saving memory (whenever scrolled, the disappeared data goes on pause and that way the app is not going to be slow as its not going to be overwhelmed
    }

    inner class ArtistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //the data positioned on the layout of the activity
        val artistImage = itemView.findViewById<ImageView>(R.id.artistListImage) //from artist_list layout
        val artistName = itemView.findViewById<TextView>(R.id.artist_list_textView)

        //making the conditions for the returned data of the sent request
        fun bindArtist(artist:ArtistData?) {
            if ( artist == null ) return

            if ( artistName != null)
                artistName.text = artist.name

            if (artistImage != null)
                Picasso.get().load(artist.picture_big).into(artistImage) //picasso function for showing the pictures


            itemView.setOnClickListener {onArtistClick(artist)} //item view as onClick
        }
    }
}