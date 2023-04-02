package nikitagorbatko.fojin.test.reviews.ui.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.databinding.ItemReviewBinding

class ReviewsAdapter(private val onItemClick: (String) -> Unit) :
    PagingDataAdapter<ReviewDto, ReviewsAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = getItem(position)

        with(holder.binding) {
            Glide.with(root)
                .load(review?.multimedia?.src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imageViewReview)
            textViewReviewTitle.text = review?.displayTitle
            textViewReviewArticle.text = review?.headline
            textViewAuthor.text = review?.byline
            textViewData.text = review?.dateUpdated
            buttonRead.setOnClickListener {
                review?.link?.url?.let { it1 -> onItemClick(it1) }
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<ReviewDto>() {

    override fun areItemsTheSame(oldItem: ReviewDto, newItem: ReviewDto): Boolean {
        return oldItem.displayTitle == newItem.displayTitle
                && oldItem.byline == newItem.byline
                && oldItem.publicationDate == newItem.publicationDate
    }

    override fun areContentsTheSame(oldItem: ReviewDto, newItem: ReviewDto): Boolean {
        return oldItem.dateUpdated == newItem.dateUpdated
    }
}