package nikitagorbatko.fojin.test.reviews.entity

import androidx.room.ColumnInfo

interface Critic {
    val displayName: String?
    val sortName: String?
    val status: String?
    val bio: String?
    val seoName: String?

    //simplification instead of relation with resource and multimedia
    val resourceType: String?
    val resourceSrc: String?
    val resourceHeight: Int?
    val resourceWidth: Int?
    val resourceCredit: String?
}