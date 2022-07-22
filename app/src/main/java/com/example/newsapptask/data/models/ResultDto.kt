package com.example.newsapptask.data.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ResultDto(
    @SerializedName("abstract")
    @Expose
    var `abstract`: String?,
    @SerializedName("byline")
    @Expose
    var byline: String,
    @SerializedName("created_date")
    @Expose
    var createdDate: String,
    @SerializedName("des_facet")
    @Expose
    var desFacet: List<String>?,
    @SerializedName("first_published_date")
    @Expose
    var firstPublishedDate: String,
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<String>?,
    @SerializedName("item_type")
    @Expose
    var itemType: String,
    @SerializedName("kicker")
    @Expose
    var kicker: String,
    @SerializedName("material_type_facet")
    @Expose
    var materialTypeFacet: String,
    @SerializedName("multimedia")
    @Expose
    var multimediaDto: List<MultimediaDto>?,
    @SerializedName("org_facet")
    @Expose
    var orgFacet: List<String>?,
    @SerializedName("per_facet")
    @Expose
    var perFacet: List<String>?,
    @SerializedName("published_date")
    @Expose
    var publishedDate: String,
    @SerializedName("related_urls")
    @Expose
    var relatedUrls: Any?,
    @SerializedName("section")
    @Expose
    var section: String,
    @SerializedName("slug_name")
    @Expose
    var slugName: String,
    @SerializedName("source")
    @Expose
    var source: String,
    @SerializedName("subheadline")
    @Expose
    var subheadline: String,
    @SerializedName("subsection")
    @Expose
    var subsection: String,
    @SerializedName("thumbnail_standard")
    @Expose
    var thumbnailStandard: String?,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("updated_date")
    @Expose
    var updatedDate: String,
    @SerializedName("uri")
    @Expose
    var uri: String,
    @SerializedName("url")
    @Expose
    var url: String
)