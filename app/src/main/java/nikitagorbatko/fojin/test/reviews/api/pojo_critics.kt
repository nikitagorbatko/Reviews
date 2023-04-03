package nikitagorbatko.fojin.test.reviews.api


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CriticsResponseDto(
    @Json(name = "status") val status: String?,
    @Json(name = "copyright") val copyright: String?,
    @Json(name = "num_results") val numResults: Int?,
    @Json(name = "results") val results: List<ResultDto?>?
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ResultDto(
    @Json(name = "display_name") val displayName: String?,
    @Json(name = "sort_name") val sortName: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "seo_name") val seoName: String?,
    @Json(name = "multimedia") val multimedia: CriticMultimediaDto?
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ResourceDto(
    @Json(name = "type") val type: String?,
    @Json(name = "src") val src: String?,
    @Json(name = "height") val height: Int?,
    @Json(name = "width") val width: Int?,
    @Json(name = "credit") val credit: String?
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CriticMultimediaDto(
    @Json(name = "resource") val resource: ResourceDto?
): Parcelable