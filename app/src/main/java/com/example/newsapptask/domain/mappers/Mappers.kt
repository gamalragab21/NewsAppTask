package com.example.newsapptask.domain.mappers

import com.example.newsapptask.data.models.NewsDto
import com.example.newsapptask.data.models.ResultDto
import com.example.newsapptask.domain.models.NewsData


fun ResultDto.toNewsData(): NewsData = NewsData(
    abstract,
    byline,
    createdDate,
    desFacet,
    firstPublishedDate,
    geoFacet,
    itemType,
    kicker,
    materialTypeFacet,
    multimediaDto,
    orgFacet,
    perFacet,
    publishedDate,
    relatedUrls,
    section,
    slugName,
    source,
    subheadline,
    subsection,
    thumbnailStandard,
    title,
    updatedDate,
    uri,
    url
)