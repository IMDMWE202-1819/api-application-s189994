package com.example.administrator.apiapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.models.AlbumData
import com.squareup.picasso.Picasso

//making the Adapter class for the Artist page, which will fit artist data into the layout objects.
class AlbumAdapter (private val data:List<AlbumData>, private val context: Context, val onAlbumClick: (album: AlbumData) -> Unit ) : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() { //making the onClick for albums
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.AlbumHolder { //creating a viewHolder because the Adapter needs an implementation of the original abstract class
            val albumView = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false) // inflater using the layout with picture box and text so we can make a place for the API to send the data to them
            return AlbumHolder(albumView)
        }

        override fun getItemCount(): Int {
            return data.size //returns the number of current items available in the Album Adapter
        }

        override fun onBindViewHolder(holder: AlbumAdapter.AlbumHolder, position: Int) {
            holder.bindAlbum(data[position])  //using to cache the view objects for saving memory (whenever scrolled, the disappeared data goes on pause and that way the app is not going to be slow as its not going to be overwhelmed
        }

        inner class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val albumImage = itemView.findViewById<ImageView>(R.id.albumListImage)  //the data positioned on the layout of the activity
            val albumName = itemView.findViewById<TextView>(R.id.album_list_textView) //from album_list layout

            fun bindAlbum (album: AlbumData?) { //function allowing the data to be bind from the models directly to xml
                //making the conditions for the returned data of the sent request
                if ( album == null ) return

                if ( albumName != null)
                    albumName.text = album.title

                if (albumImage != null)
                    Picasso.get().load(album.cover_big).into(albumImage) //Picasso funtion for showing the pictures on the picture boxes from the layouts

                itemView.setOnClickListener {onAlbumClick(album)} //item viewing as onClick function
            }
        }
}