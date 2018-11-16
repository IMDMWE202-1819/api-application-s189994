package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize //function Parcelize - for easier pulling data
data class DeezerAlbumResult( //the result as a list, and like a queue
    val data: List<AlbumData>,
    val next: String? = null,
    val prev: String? = null,
    val total: Int
) : Parcelable

@Parcelize
data class AlbumData( //the album data from the API
    val cover_big: String,
    val id: String,
    val release_date: String,
    val title: String,
    val tracklist: String
): Parcelable

class AlbumConverter: Converter { //making the converter
    override fun canConvert(cls: Class<*>): Boolean {
        return cls == AlbumData::class.java
    }

    override fun fromJson(jv: JsonValue): Any {
        val album: JsonObject? = jv.obj

        if (album != null) { //if the album data is with empty data , then send response for these 5 strings
        return AlbumData(
                album["cover_big"].toString(),
                album["id"].toString(),
                album["release_date"].toString(),
                album["title"].toString(),
                album["tracklist"].toString()
            )
        }
        return "invalid" // if the album data is nothing, return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}