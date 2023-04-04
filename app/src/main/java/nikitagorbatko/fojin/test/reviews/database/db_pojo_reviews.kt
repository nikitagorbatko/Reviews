package nikitagorbatko.fojin.test.reviews.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import nikitagorbatko.fojin.test.reviews.entity.Review


@Entity(tableName = "review_table", primaryKeys = ["publication_date", "display_title"])
data class ReviewDbo(
    @ColumnInfo(name = "display_title") override val displayTitle: String,
    @ColumnInfo(name = "mpaa_rating") override val mpaaRating: String?,
    @ColumnInfo(name = "critics_pick") override val criticsPick: Int?,
    @ColumnInfo(name = "byline") override val byline: String?,
    @ColumnInfo(name = "headline") override val headline: String?,
    @ColumnInfo(name = "summary_short") override val summaryShort: String?,
    @ColumnInfo(name = "publication_date") override val publicationDate: String,
    @ColumnInfo(name = "opening_date") override val openingDate: String?,
    @ColumnInfo(name = "date_updated") override val dateUpdated: String?,

    //simplification instead of relation with link
    @ColumnInfo(name = "link_type") override val linkType: String?,
    @ColumnInfo(name = "link_url") override val linkUrl: String?,
    @ColumnInfo(name = "link_suggested_link_text") override val linkSuggestedLinkText: String?,

    //simplification instead of relation with multimedia
    @ColumnInfo(name = "multimedia_type") override val multimediaType: String?,
    @ColumnInfo(name = "multimedia_src") override val multimediaSrc: String?,
    @ColumnInfo(name = "multimedia_height") override val multimediaHeight: Int?,
    @ColumnInfo(name = "multimedia_width") override val multimediaWidth: Int?
) : Review
