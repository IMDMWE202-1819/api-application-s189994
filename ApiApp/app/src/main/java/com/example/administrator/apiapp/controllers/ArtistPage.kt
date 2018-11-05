package com.example.administrator.apiapp.controllers

import android.app.Activity
import android.os.Bundle
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.models.ArtistData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_page.*

class ArtistPage : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_page)

        val artist = intent.getParcelableExtra<ArtistData>(EXTRA_ARTIST)

        Picasso.get().load(artist.picture_big).into(artist_imageView)
        artist_list_textView.text = artist.name
    }

}
