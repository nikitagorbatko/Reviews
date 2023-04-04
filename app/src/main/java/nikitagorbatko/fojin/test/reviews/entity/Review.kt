package nikitagorbatko.fojin.test.reviews.entity


interface Review {
    val displayTitle: String?
    val mpaaRating: String?
    val criticsPick: Int?
    val byline: String?
    val headline: String?
    val summaryShort: String?
    val publicationDate: String?
    val openingDate: String?
    val dateUpdated: String?

    //simplification instead of relation with link
    val linkType: String?
    val linkUrl: String?
    val linkSuggestedLinkText: String?

    //simplification instead of relation with multimedia
    val multimediaType: String?
    val multimediaSrc: String?
    val multimediaHeight: Int?
    val multimediaWidth: Int?
}