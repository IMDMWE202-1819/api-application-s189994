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

const val EXTRA_ARTIST = "artist"

class ArtistSearch : Activity() {

    val artists = arrayListOf<ArtistData>()
    var adapter: ArtistAdapter = ArtistAdapter(artists, this) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_ARTIST, it)
        }

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_search)

        SearchBar.addTextChangedListener(SearchBarWatcher())

        val orientation = resources.configuration.orientation

        var spanCount = 2

        if ( orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 3

        artistRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        artistRecyclerView.adapter = adapter
    }

    fun onDataChanged() {
        adapter.notifyDataSetChanged()
    }

    inner class SearchBarWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) { }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if ( s != null && s.length >= 2) {
                print("in OnTextChanged")
                async.get("https://api.deezer.com/search/artist?q=$s", onResponse = {
                    val result: DeezerSearchResult = Klaxon()
                                                        .converter(ArtistConverter())
                                                        .parse(this.text)!!

                    // 3a
                    for (artist in result.data) {
                        artists.add(artist)
                    }

                    // use Klaxon (https://github.com/cbeust/klaxon for documentation) to parse the text value we get back from Deezer.
                    // 1. add klaxon implementation to build.gradle (Module: app) + sync (done)
                    // 2. Klaxon().parse<ArtistData>(this.text)
                    // 3. store results into a list of ArtistData
                    // 3a. loop through result.data
                    // 3b. add each artist to the artists list
                    // We need to get the information from the text format into the ArtistData from DeezerSearchResult.kt
                    runOnUiThread {  }
                } )
            }
        }

    }
}
