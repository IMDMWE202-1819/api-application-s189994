package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeezerSearchResult(
    val data: List<ArtistData>,
    val next: String? = null,
    val total: Int
) : Parcelable

@Parcelize
data class ArtistData(
    val id: String?,
    val link: String?,
    val name: String?,
    val nb_album: Int?,
    val nb_fan: Int?,
    val picture: String?,
    val picture_big: String?,
    val picture_medium: String?/*,
    val picture_small: String?,
    val picture_xl: String?,
    val radio: Boolean?,
    val tracklist: String?,
    val type: String?*/
) : Parcelable

class ArtistConverter : Converter {
    override fun canConvert(cls: Class<*>): Boolean {
        return cls == ArtistData::class.java
    }

    override fun fromJson(jv: JsonValue): Any {
        val artist: JsonObject? = jv.obj

        if (artist != null) {
            return ArtistData(
                artist.int("id").toString(),
                artist.string("link"),
                artist.string("name"),
                artist.int("nb_album"),
                artist.int("nb_fan"),
                artist.string("picture"),
                artist.string("picture_big"),
                artist.string("picture_medium")
            )
        }

        return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}