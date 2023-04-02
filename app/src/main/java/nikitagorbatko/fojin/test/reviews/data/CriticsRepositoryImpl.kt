package nikitagorbatko.fojin.test.reviews.data

import android.os.Parcel
import android.os.Parcelable
import nikitagorbatko.fojin.test.reviews.api.CriticsResponseDto
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews


class CriticsRepositoryImpl private constructor(private val retrofit: RetrofitReviews) :
    CriticsRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: CriticsRepository? = null

        fun getInstance(retrofit: RetrofitReviews): CriticsRepository {
            return if (INSTANCE == null) {
                INSTANCE = CriticsRepositoryImpl(retrofit)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }


    override suspend fun getCritics(): CriticsResponseDto? {
        return retrofit.reviewsApi.getCritics()
    }
}