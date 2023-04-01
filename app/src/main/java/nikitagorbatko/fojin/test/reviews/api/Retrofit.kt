package nikitagorbatko.fojin.test.reviews.api

import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nytimes.com/svc/movies/"
private const val API_KEY = "kGttkCzGWzC3AZs38DroAJKlqTTcGY9P"

object RetrofitReviews {
    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val reviewsApi: ReviewsApi = retrofit.create(ReviewsApi::class.java)
}

interface ReviewsApi {
    @GET("v2/reviews/search.json?api-key=${API_KEY}")
    suspend fun getReviews(@Query("offset") offset: Int): ReviewsResponseDto?
}