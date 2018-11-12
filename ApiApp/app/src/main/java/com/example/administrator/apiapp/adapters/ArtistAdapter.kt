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

class ArtistAdapter(private val data:List<ArtistData>, private val context:Context, val onArtistClick: (artist:ArtistData) -> Unit ) : RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAdapter.ArtistHolder {
        val artistView = LayoutInflater.from(context).inflate(R.layout.artist_list_item, parent, false)
        return ArtistHolder(artistView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArtistAdapter.ArtistHolder, position: Int) {
        holder.bindArtist(data[position])
    }

    inner class ArtistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistImage = itemView.findViewById<ImageView>(R.id.artistListImage)
        val artistName = itemView.findViewById<TextView>(R.id.artist_list_textView)

        fun bindArtist(artist:ArtistData?) {
            if ( artist == null ) return

            if ( artistName != null)
                artistName.text = artist.name

            if (artistImage != null)
                Picasso.get().load(artist.picture_big).into(artistImage)


            itemView.setOnClickListener {onArtistClick(artist)}
        }
    }
}