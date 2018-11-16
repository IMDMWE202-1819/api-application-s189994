package com.example.administrator.apiapp.controllers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.beust.klaxon.Klaxon
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.adapters.TrackAdapter
import com.example.administrator.apiapp.models.*
import com.squareup.picasso.Picasso
import khttp.async.Companion.get
import kotlinx.android.synthetic.main.activity_album_page.*




class AlbumPage : Activity() {

    val tracks = arrayListOf<TrackData>() //empty list of tracks
    lateinit var album:AlbumData //backing field that stores the data

    var adapter: TrackAdapter = TrackAdapter (tracks,this ) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_page)

        album = intent.getParcelableExtra<AlbumData>(EXTRA_ALBUM)

        Picasso.get().load(album.cover_big).into(album_imageView) //picasso function for visualising the pictures
        album_textView.text = album.title
        release_date_textView3.text = "Release Date: " + "" + album.release_date //more data for show


        trackRecyclerView.adapter = adapter

        trackRecyclerView.layoutManager = LinearLayoutManager(this)

        retrieveTracks()
    }
//get request
    private fun retrieveTracks () { //make request
            get("https://api.deezer.com/album/${album.id}/tracks", onResponse = {
                val result: DeezerTrackResult = Klaxon()
                    .converter(TrackConverter())
                    .parse(this.text)!!

                tracks.clear() //clearing the result

                for (track: TrackData in result.data) {
                    tracks.add(track) //adding data to result
                }

                runOnUiThread { adapter.notifyDataSetChanged()}
                })
            }
    }
