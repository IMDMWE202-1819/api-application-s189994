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
import khttp.async.Companion.get
import kotlinx.android.synthetic.main.activity_artist_page.*


const val EXTRA_ALBUM = "album" //extra_message

class ArtistPage : Activity() {

    val albums = arrayListOf<AlbumData>() //returning an empty array list for albums list
    lateinit var artist:ArtistData
    var adapter: AlbumAdapter = AlbumAdapter(albums, this) {
        val intent = Intent(this, AlbumPage::class.java).apply { //after pressing the Album picture , directs the user to the Album page with the tracks
            putExtra(EXTRA_ALBUM, it) //adds the Album's value to the intent
        }

        startActivity(intent) //next page whenever onClick event
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_page) //connection between the layout and the activity

        artist = intent.getParcelableExtra<ArtistData>(EXTRA_ARTIST) //moving data from one page/activity to the other with onClick

        Picasso.get().load(artist.picture_big).into(artist_imageView) //dublicated picture
        artist_list_textView.text = artist.name//dublicated name
        //adding more information for the artist
        fans_textView2.text = "Number of Fans: " + "" + artist.nb_fan.toString()
        nb_albums_textView3.text = "Number of Albums: " + "" + artist.nb_album.toString()
        link_textView5.text = "Link for Artist: " + "" + artist.link.toString()


//setting the orientation - how many pictures(with names) should show the result shows
        val orientation = resources.configuration.orientation

        var spanCount = 2

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 2


        albumRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        albumRecyclerView.adapter = adapter

        retrieveAlbums()

    }

    private fun retrieveAlbums() { //making the API request for data
        get("https://api.deezer.com/artist/${artist.id}/albums", onResponse = {
            val result: DeezerAlbumResult = Klaxon() //
                .converter(AlbumConverter())
                .parse(this.text)!!

            albums.clear() //to clear the result, not dublicating

            for (album in result.data) {
                albums.add(album) //adding more albums on the result
            }

            runOnUiThread { adapter.notifyDataSetChanged() }
        })
    }
}


