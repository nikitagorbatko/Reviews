package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.database.ReviewDbo
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

object ReviewsDboToUiMapper {
    fun convert(dtoReviews: List<ReviewDbo>): List<ReviewUi> {
        val uiReviews = mutableListOf<ReviewUi>()
        dtoReviews.forEach {
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
                    linkType = it.linkType,
                    linkUrl = it.linkUrl,
                    linkSuggestedLinkText = it.linkSuggestedLinkText,
                    multimediaType = it.multimediaType,
                    multimediaSrc = it.multimediaSrc,
                    multimediaHeight = it.multimediaHeight,
                    multimediaWidth = it.multimediaWidth
                )
            )
        }
        return uiReviews
    }
}