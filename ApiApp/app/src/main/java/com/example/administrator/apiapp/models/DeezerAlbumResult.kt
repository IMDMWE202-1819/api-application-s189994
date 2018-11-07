package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeezerAlbumResult(
    val data: List<AlbumData>,
    val next: String? = null,
    val prev: String? = null,
    val total: Int
) : Parcelable

@Parcelize
data class AlbumData(
    val cover_big: String,
    val id: String,
    val release_date: String,
    val title: String,
    val tracklist: String
): Parcelable

class AlbumConverter: Converter {
    override fun canConvert(cls: Class<*>): Boolean {
        return cls == AlbumData::class.java
    }

    override fun fromJson(jv: JsonValue): Any {
        val album: JsonObject? = jv.obj

        if (album != null) {
        return AlbumData(
                album["cover_big"].toString(),
                album["id"].toString(),
                album["release_date"].toString(),
                album["title"].toString(),
                album["tracklist"].toString()
            )
        }
        return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}