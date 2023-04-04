package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.database.ReviewDbo

object ReviewsDtoToDboMapper {
    fun convert(dtoReviews: List<ReviewDto>): List<ReviewDbo> {
        val dboReviews = mutableListOf<ReviewDbo>()
        dtoReviews.forEach {
            dboReviews.add(
                ReviewDbo(
                    displayTitle = it.displayTitle!!,
                    mpaaRating = it.mpaaRating,
                    criticsPick = it.criticsPick,
                    byline = it.byline,
                    headline = it.headline,
                    summaryShort = it.summaryShort,
                    publicationDate = it.publicationDate!!,
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
        return dboReviews
    }
}