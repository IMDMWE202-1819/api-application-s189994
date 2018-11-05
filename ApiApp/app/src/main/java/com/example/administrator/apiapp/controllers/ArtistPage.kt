package com.example.administrator.apiapp.controllers

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.beust.klaxon.Klaxon
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.adapters.AlbumAdapter
import com.example.administrator.apiapp.models.*
import com.squareup.picasso.Picasso
import khttp.async
import khttp.async.Companion.get
import kotlinx.android.synthetic.main.activity_artist_page.*


const val EXTRA_ALBUM = "album"

class ArtistPage : Activity() {

    val albums = arrayListOf<AlbumData>()
    var adapter: AlbumAdapter = AlbumAdapter(albums, this) {
        val intent = Intent(this, MainActivity::class.java).apply {intent
            putExtra(EXTRA_ALBUM, booleanArrayOf())
        }

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_page)

        val artist = intent.getParcelableExtra<ArtistData>(EXTRA_ARTIST)

        Picasso.get().load(artist.picture_big).into(artist_imageView)
        artist_list_textView.text = artist.name


        val orientation = resources.configuration.orientation

        var spanCount = 2

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 3

        albumRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        albumRecyclerView.adapter = adapter

    }

    if (AlbumData != null) {
        get("https://api.deezer.com/artist/27/albums", onResponse = {
            val result: DeezerSearchResult = Klaxon()
                    .converter( AlbumConverter())
                    .parse(this.text)!!

    albums.clear()

        for (artist in result.data) {
            albums.add(data)
        }
    }

        runOnUiThread {()}
    }}


