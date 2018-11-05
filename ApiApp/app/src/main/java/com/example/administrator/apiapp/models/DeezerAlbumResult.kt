package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeezerAlbumResult(
    val name: String,
    val age: Int,
    val email: String,
    val phone: Long
): Parcelable


@Parcelize
data class AlbumData(

    val id: String?,
    val title: String?,
    val cover: String?,
    val nb_fan: Int?,
    val cover_small: String?,
    val cover_medium: String?,
    val cover_big: String?
) : Parcelable

class AlbumConverter: Converter {
    override fun canConvert(cls: Class<*>): Boolean {
        return cls == AlbumData::class.java
    }

    override fun fromJson(jv: JsonValue): Any {
        val album: JsonObject? = jv.obj

        if (album != null) {
        return AlbumData(
                album.int("id").toString(),
                album.string("title"),
                album.string("cover"),
                album.int("nb_fan"),
                album.string("cover_small"),
                album.string("cover_medium"),
                album.string("cover_big")
            )
        }
        return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}