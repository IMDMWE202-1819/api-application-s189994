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

class AlbumAdapter (private val data:List<AlbumData>, private val context: Context, val onAlbumClick: (album: AlbumData) -> Unit ) : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.AlbumHolder {
            val albumView = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false)
            return AlbumHolder(albumView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: AlbumAdapter.AlbumHolder, position: Int) {
            holder.bindAlbum(data[position])
        }

        inner class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val albumImage = itemView.findViewById<ImageView>(R.id.albumListImage)
            val albumName = itemView.findViewById<TextView>(R.id.album_list_textView)

            fun bindAlbum (album: AlbumData?) {
                if ( album == null ) return

                if ( albumName != null)
                    albumName.text = album.title

                if (albumImage != null)
                    Picasso.get().load(album.cover_big).into(albumImage)

                itemView.setOnClickListener {onAlbumClick(album)}
            }
        }
}