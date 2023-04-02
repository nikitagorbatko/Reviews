package nikitagorbatko.fojin.test.reviews.ui.reviews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.map
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment() {
    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var job: Job

    private val viewModel by viewModels<ReviewsViewModel> {
        ReviewsViewModel.Companion.ReviewsViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reviewAdapter = ReviewsAdapter {
            try {
                val uri: Uri = Uri.parse(it)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (_: Exception) {}
        }

        binding.recyclerReviews.adapter =
            reviewAdapter.withLoadStateFooter(footer = ReviewsLoadStateAdapter { })

        job = viewModel.getPagedReviews().onEach {
            reviewAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.swipeContainer.setOnRefreshListener {
            reviewAdapter.refresh()
            job.cancel()
            viewModel.invalidate()
            viewModel.getPagedReviews().onEach {
                reviewAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }


//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.pagedReviews.collectLatest { pagingData ->
//                    reviewAdapter.submitData(pagingData)
//                }
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                reviewAdapter.loadStateFlow.collect {
                    binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
                    binding.textViewError.isVisible = it.source.refresh is LoadState.Error
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}