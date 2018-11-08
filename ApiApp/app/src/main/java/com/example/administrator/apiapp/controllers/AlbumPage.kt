package com.example.administrator.apiapp.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.beust.klaxon.Klaxon
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.adapters.TrackAdapter
import com.example.administrator.apiapp.models.*
import com.squareup.picasso.Picasso
import khttp.async.Companion.get
import kotlinx.android.synthetic.main.activity_album_page.*


    const val EXTRA_TRACK= "track"

class AlbumPage : AppCompatActivity() {

    val tracks = arrayListOf<TrackData>()
    lateinit var album:AlbumData
    var adapter: TrackAdapter = TrackAdapter (tracks,this ) {
        val intent = Intent(this, MainActivity::class.java).apply {intent
            putExtra(EXTRA_TRACK, booleanArrayOf())
        }

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_page)

        album = intent.getParcelableExtra<AlbumData>(EXTRA_ALBUM)

        Picasso.get().load(album.cover_big).into(album_imageView)
        album_textView.text = album.title

        trackRecyclerView.adapter = adapter

        retrieveTracks()
    }

    private fun retrieveTracks () {
            get("https://api.deezer.com/album/${album.id}/tracks", onResponse = {
                val result: DeezerTrackResult = Klaxon()
                    .converter(TrackConverter())
                    .parse(this.text)!!

                tracks.clear()

                for (track: TrackData in result.data) {
                    tracks.add(track)
                }

                runOnUiThread { adapter.notifyDataSetChanged()}
                })
            }
    }
