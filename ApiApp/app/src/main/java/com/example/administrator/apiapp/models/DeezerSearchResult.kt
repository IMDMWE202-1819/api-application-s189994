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
    val picture_medium: String?
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
                artist.string("link").toString(),
                artist.string("name").toString(),
                artist.int("nb_album")?.toInt(),
                artist.int("nb_fan")?.toInt(),
                artist.string("picture").toString(),
                artist.string("picture_big").toString(),
                artist.string("picture_medium").toString()
            )
        }

        return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}