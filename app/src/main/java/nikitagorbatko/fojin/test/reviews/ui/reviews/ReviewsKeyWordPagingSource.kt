package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsIntervalUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsKeyWordUseCase

class ReviewsKeyWordPagingSource(
    private val getReviewsKeyWordUseCase: GetReviewsKeyWordUseCase,
    private val keyWord: String
) : PagingSource<Int, ReviewDto>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewDto>): Int = DEFAULT_OFFSET

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewDto> {
        val offset = params.key ?: DEFAULT_OFFSET

        return kotlin.runCatching {
            getReviewsKeyWordUseCase.execute(offset, keyWord)
        }.fold(onSuccess = {
            LoadResult.Page(
                it?.results ?: emptyList(),
                null,
                if (it?.hasMore == true) offset + OFFSET_STEP else null
            )
        }, onFailure = { LoadResult.Error(it) })
    }

    companion object {
        private const val DEFAULT_OFFSET = 0
        private const val OFFSET_STEP = 20
    }
}