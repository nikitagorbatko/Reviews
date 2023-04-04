package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

object ReviewsDtoToUiMapper {
    fun convert(dtoReviews: List<ReviewDto>?): List<ReviewUi> {
        val uiReviews = mutableListOf<ReviewUi>()
        dtoReviews?.forEach {
            uiReviews.add(
                ReviewUi(
                    displayTitle = it.displayTitle,
                    mpaaRating = it.mpaaRating,
                    criticsPick = it.criticsPick,
                    byline = it.byline,
                    headline = it.headline,
                    summaryShort = it.summaryShort,
                    publicationDate = it.publicationDate,
                    openingDate = it.openingDate,
                    dateUpdated = it.dateUpdated,
                    linkType = it.link?.type,
                    linkUrl = it.link?.url,
                    linkSuggestedLinkText = it.link?.suggestedLinkText,
                    multimediaType = it.multimedia?.type,
                    multimediaSrc = it.multimedia?.src,
                    multimediaHeight = it.multimedia?.height,
                    multimediaWidth = it.multimedia?.width
                )
            )
        }
        return uiReviews
    }
}