package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize //same as the searchResult model and AlbumResult model. Only the data names are different
data class DeezerTrackResult(
    val `data`: List<TrackData>,
    val next: String? = null,
    val prev: String? = null,
    val total: Int
):Parcelable

@Parcelize
data class TrackData(
    val duration: String,
    val id: String,
    val preview: String,
    val rank: String,
    val title: String,
    val track_position: String
):Parcelable

 class TrackConverter : Converter {
     override fun canConvert(cls: Class<*>): Boolean {
         return cls == TrackData::class.java
     }

     override fun fromJson(jv: JsonValue): Any {
         val track: JsonObject? = jv.obj


         if (track != null) {
             return TrackData(
                 track["duration"].toString(),
                 track["id"].toString(),
                 track["preview"].toString(),
                 track["rank"].toString(),
                 track["title"].toString(),
                 track["track_position"]. toString()
             )
         }

         return "invalid"
     }
     override fun toJson(value: Any): String {
         return "{}"
     }
 }
