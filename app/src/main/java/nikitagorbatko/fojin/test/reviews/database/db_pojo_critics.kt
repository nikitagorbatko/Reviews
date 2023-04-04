package nikitagorbatko.fojin.test.reviews.database

import androidx.room.*
import nikitagorbatko.fojin.test.reviews.entity.Critic

@Entity(tableName = "critic_table")
data class CriticDbo(
    @PrimaryKey
    @ColumnInfo(name = "display_name") override val displayName: String,
    @ColumnInfo(name = "sort_name") override val sortName: String?,
    @ColumnInfo(name = "status") override val status: String?,
    @ColumnInfo(name = "bio") override val bio: String?,
    @ColumnInfo(name = "seo_name") override val seoName: String?,

    //simplification instead of relation with resource and multimedia
    @ColumnInfo(name = "resource_type") override val resourceType: String?,
    @ColumnInfo(name = "resource_src") override val resourceSrc: String?,
    @ColumnInfo(name = "resource_height") override val resourceHeight: Int?,
    @ColumnInfo(name = "resource_width") override val resourceWidth: Int?,
    @ColumnInfo(name = "resource_credit") override val resourceCredit: String?,
): Critic
