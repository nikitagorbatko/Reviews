package nikitagorbatko.fojin.test.reviews.ui.entities

import nikitagorbatko.fojin.test.reviews.entity.Review

class ReviewUi(
    override val displayTitle: String?,
    override val mpaaRating: String?,
    override val criticsPick: Int?,
    override val byline: String?,
    override val headline: String?,
    override val summaryShort: String?,
    override val publicationDate: String?,
    override val openingDate: String?,
    override val dateUpdated: String?,
    override val linkType: String?,
    override val linkUrl: String?,
    override val linkSuggestedLinkText: String?,
    override val multimediaType: String?,
    override val multimediaSrc: String?,
    override val multimediaHeight: Int?,
    override val multimediaWidth: Int?
) : Review