package com.example.administrator.apiapp.controllers

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.beust.klaxon.Klaxon
import com.example.administrator.apiapp.R
import com.example.administrator.apiapp.adapters.ArtistAdapter
import com.example.administrator.apiapp.models.ArtistConverter
import com.example.administrator.apiapp.models.ArtistData
import com.example.administrator.apiapp.models.DeezerSearchResult
import khttp.async

import kotlinx.android.synthetic.main.activity_artist_search.*

const val EXTRA_ARTIST = "artist" // artist value for the intent

class ArtistSearch : Activity() {

    val artists = arrayListOf<ArtistData>() //making an empty array list for being able to put data in it.
    var adapter: ArtistAdapter = ArtistAdapter(artists, this) { //Applying the adapter for the page
        //transition from one page to another - INTENT
        val intent = Intent(this, ArtistPage::class.java).apply {
            putExtra(EXTRA_ARTIST, it) //adds the Artist's value to the intent
        }

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_search)
        SearchBar.addTextChangedListener(SearchBarWatcher())
//setting the orientation - how many pictures(with names) should show the result shows
        val orientation = resources.configuration.orientation

        var spanCount = 2

        if ( orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 2


        artistRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        artistRecyclerView.adapter = adapter
    }

    fun onDataChanged() {
        adapter.notifyDataSetChanged()
    }
//function for text changing = result changing
    inner class SearchBarWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) { }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
//when more than 2 letters => get response
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if ( s != null && s.length >= 2) {
                async.get("https://api.deezer.com/search/artist?q=$s", onResponse = {
                    val result: DeezerSearchResult = Klaxon()
                                                        .converter(ArtistConverter())
                                                        .parse(this.text)!! //show

                    artists.clear() //result changes as it removes the past data from the page and puts only new one

                    for (artist in result.data) {
                        artists.add(artist) //adding an artist data response
                    }

                    runOnUiThread { onDataChanged()  }
                } )
            }
        }

    }
}
