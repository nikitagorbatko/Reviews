package nikitagorbatko.fojin.test.reviews.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewsResponseDto(
    @Json(name = "status") var status: String? = null,
    @Json(name = "copyright") var copyright: String? = null,
    @Json(name = "has_more") var hasMore: Boolean? = null,
    @Json(name = "num_results") var numResults: Int? = null,
    @Json(name = "results") var results: List<ReviewDto> = emptyList()
)

@JsonClass(generateAdapter = true)
data class LinkDto(
    @Json(name = "type") var type: String? = null,
    @Json(name = "url") var url: String? = null,
    @Json(name = "suggested_link_text") var suggestedLinkText: String? = null
)

@JsonClass(generateAdapter = true)
data class MultimediaDto(
    @Json(name = "type") var type: String? = null,
    @Json(name = "src") var src: String? = null,
    @Json(name = "height") var height: Int? = null,
    @Json(name = "width") var width: Int? = null
)

@JsonClass(generateAdapter = true)
data class ReviewDto(
    @Json(name = "display_title") var displayTitle: String? = null,
    @Json(name = "mpaa_rating") var mpaaRating: String? = null,
    @Json(name = "critics_pick") var criticsPick: Int? = null,
    @Json(name = "byline") var byline: String? = null,
    @Json(name = "headline") var headline: String? = null,
    @Json(name = "summary_short") var summaryShort: String? = null,
    @Json(name = "publication_date") var publicationDate: String? = null,
    @Json(name = "opening_date") var openingDate: String? = null,
    @Json(name = "date_updated") var dateUpdated: String? = null,
    @Json(name = "link") var link: LinkDto? = LinkDto(),
    @Json(name = "multimedia") var multimedia: MultimediaDto? = MultimediaDto()
)