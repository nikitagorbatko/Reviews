package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi


class ReviewsPagingSource(
    private val getReviewsUseCase: GetReviewsUseCase,
    private val getReviewsDbUseCase: GetReviewsDbUseCase,
    private val interval: Pair<String, String>? = null,
    private val keyWord: String? = null,
    private val onErrorCrutch: (message: String) -> Unit
) : PagingSource<Int, ReviewUi>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewUi>): Int = DEFAULT_OFFSET

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewUi> {
        val offset = params.key ?: DEFAULT_OFFSET

        return kotlin.runCatching {
            getReviewsUseCase.execute(offset, interval, keyWord)
        }.fold(onSuccess = {
            LoadResult.Page(
                it,
                null,
                if (it.isNotEmpty()) offset + OFFSET_STEP else null
            )
        }, onFailure = {
            val reviews = getReviewsDbUseCase.execute(interval, keyWord)
            if (offset == DEFAULT_OFFSET && reviews.isNotEmpty()) {
                onErrorCrutch("No internet connection")
                LoadResult.Page(
                    reviews,
                    null,
                    null
                )
            } else {
                onErrorCrutch("No internet connection")
                LoadResult.Error(it)
            }
        })
    }

    companion object {
        private const val DEFAULT_OFFSET = 0
        private const val OFFSET_STEP = 20
    }
}