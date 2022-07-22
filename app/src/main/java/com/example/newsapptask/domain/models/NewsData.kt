package com.example.newsapptask.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.example.newsapptask.data.models.MultimediaDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NewsData(
    var abstract: String?,
    var byline: String,
    var createdDate: String,
    var desFacet: List<String>?,
    var firstPublishedDate: String,
    var geoFacet: List<String>?,
    var itemType: String,
    var kicker: String,
    var materialTypeFacet: String,
    var multimediaDto: List<MultimediaDto>?,
    var orgFacet: List<String>?,
    var perFacet: List<String>?,
    var publishedDate: String,
    var relatedUrls: Any?,
    var section: String,
    var slugName: String,
    var source: String,
    var subheadline: String,
    var subsection: String,
    var thumbnailStandard: String?,
    var title: String,
    var updatedDate: String,
    var uri: String,
    var url: String
):Parcelable {
    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }
}
