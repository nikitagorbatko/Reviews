package nikitagorbatko.fojin.test.reviews.ui.single_critic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.R
import nikitagorbatko.fojin.test.reviews.api.CriticDto
import nikitagorbatko.fojin.test.reviews.database.ReviewsDatabase
import nikitagorbatko.fojin.test.reviews.databinding.FragmentSingleCriticBinding
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.ui.reviews.ReviewsAdapter
import nikitagorbatko.fojin.test.reviews.ui.reviews.ReviewsLoadStateAdapter


class SingleCriticFragment : Fragment() {
    private var _binding: FragmentSingleCriticBinding? = null
    private val binding get() = _binding!!
    private lateinit var reviewAdapter: ReviewsAdapter
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter: JsonAdapter<CriticUi> = moshi.adapter(CriticUi::class.java)

    private val viewModel by viewModels<SingleCriticFragmentViewModel> {
        SingleCriticFragmentViewModel.Companion.SingleCriticFragmentViewModelFactory(
            ReviewsDatabase.getDatabase(requireContext()).getReviewsDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCriticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val critic = arguments?.getString("critic")?.let {
            jsonAdapter.fromJson(it)
        }

        bind(critic)
        observe(critic)
    }

    private fun bind(critic: CriticUi?) {
        reviewAdapter = ReviewsAdapter {
            try {
                val uri: Uri = Uri.parse(it)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (_: Exception) {
            }
        }
        binding.recyclerReviews.adapter =
            reviewAdapter.withLoadStateFooter(footer = ReviewsLoadStateAdapter())

        binding.collapsingToolbar.title = critic?.displayName

        Glide.with(binding.root)
            .load(critic?.resourceSrc)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .error(R.drawable.ic_person_circle)
            .into(binding.imageViewSingleCritic)
    }

    private fun observe(critic: CriticUi?) {
        if (critic?.displayName != null) {
            viewModel.pagedReviews(critic.displayName).onEach {
                reviewAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorChannel.collect {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}