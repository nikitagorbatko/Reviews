package nikitagorbatko.fojin.test.reviews.ui.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import nikitagorbatko.fojin.test.reviews.databinding.LoadStateViewBinding

class ReviewsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ReviewsLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(val binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
//        with(holder.binding) {
//            loadStateRetryButton.isVisible = loadState !is LoadState.Loading
//            loadStateErrorTextView.isVisible = loadState !is LoadState.Loading
//            loadStateProgress.isVisible = loadState is LoadState.Loading
//
//            if (loadState is LoadState.Error) {
//                loadStateErrorTextView.text = loadState.error.localizedMessage
//            }
//            loadStateRetryButton.setOnClickListener {
//                retry.invoke()
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }
}

//class MyLoadStateAdapter(
//    private val retry: () -> Unit
//) : LoadStateAdapter<LoadStateViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
//        LoadStateViewHolder(parent, retry)
//
//    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
//        holder.bind(loadState)
//}