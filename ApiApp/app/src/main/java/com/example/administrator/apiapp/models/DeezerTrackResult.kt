package com.example.administrator.apiapp.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val title: String
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
                 track["title"].toString(),
                 track["rank"].toString(),
                 track["preview"].toString()
             )
         }

         return "invalid"
     }
     override fun toJson(value: Any): String {
         return "{}"
     }
 }
