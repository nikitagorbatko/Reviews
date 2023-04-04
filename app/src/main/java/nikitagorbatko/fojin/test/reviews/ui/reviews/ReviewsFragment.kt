package nikitagorbatko.fojin.test.reviews.ui.reviews

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.R
import nikitagorbatko.fojin.test.reviews.database.ReviewsDatabase
import nikitagorbatko.fojin.test.reviews.databinding.FragmentReviewsBinding
import java.text.SimpleDateFormat
import java.util.Date

class ReviewsFragment : Fragment() {
    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var requestJob: Job
    private lateinit var reviewAdapter: ReviewsAdapter

    private val viewModel by viewModels<ReviewsFragmentViewModel> {
        ReviewsFragmentViewModel.Companion.ReviewsViewModelFactory(
            ReviewsDatabase.getDatabase(requireContext()).getReviewsDao()
        )
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
        //binding.searchView.color
        reviewAdapter = ReviewsAdapter {
            try {
                val uri: Uri = Uri.parse(it)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (_: Exception) {
            }
        }

        bind()
        observe()
    }


    /** observes viewmodel */
    private fun observe() {
        requestJob = viewModel.pagedReviews().onEach {
            reviewAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                reviewAdapter.loadStateFlow.collect {
                    binding.swipeRefreshLayout.isRefreshing = it.source.refresh is LoadState.Loading
                    binding.textViewError.isVisible = it.source.append is LoadState.Error
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorChannel.collect {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    /** binds views */
    private fun bind() {
        binding.recyclerReviews.adapter =
            reviewAdapter.withLoadStateFooter(footer = ReviewsLoadStateAdapter())


        binding.searchView.setOnSearchClickListener {
            binding.textViewTitle.visibility = View.GONE
        }

        binding.searchView.setOnCloseListener {
            binding.textViewTitle.visibility = View.VISIBLE
            return@setOnCloseListener false
        }

        binding.imageViewCalendar.setOnClickListener {
            showOnToolbarCalendarPopup(it)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyWord: String?): Boolean {
                cancelAndClean()
                requestJob = viewModel.pagedReviews(keyWord = keyWord!!).onEach {
                    reviewAdapter.submitData(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        //Не смог добиться корректной работы reviewAdapter.refresh()
        binding.swipeRefreshLayout.setOnRefreshListener {
            cancelAndClean()
            requestJob = viewModel.pagedReviews().onEach {
                reviewAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
            //reviewAdapter.refresh()
        }
    }

    private fun showOnToolbarCalendarPopup(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.dropdown_calendar_menu)

        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.custom -> {
                    cancelAndClean()
                    showDatePicker()
                }
                R.id.full -> {
                    cancelAndClean()
                    requestJob = viewModel.pagedReviews().onEach {
                        reviewAdapter.submitData(it)
                    }.launchIn(viewLifecycleOwner.lifecycleScope)
                }
            }
            true
        }


        popup.show()
    }

    /**
     * Отменяет job и очищает recycler adapter
     * Возможно стоит сделать универсальный pagingSource и не хранить ссылку на job`ы экземпляров
     * Pager`ов
     * */
    private fun cancelAndClean() {
        requestJob.cancel()
        reviewAdapter.submitData(lifecycle, PagingData.empty())
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(getString(R.string.app_name))
            .build()

        datePicker.addOnPositiveButtonClickListener {
            cancelAndClean()

            requestJob =
                viewModel.pagedReviews(
                    interval = Pair(
                        it.first.convertToDate(),
                        it.second.convertToDate()
                    )
                ).onEach { pagingData ->
                    reviewAdapter.submitData(pagingData)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        datePicker.addOnNegativeButtonClickListener {
            reviewAdapter.notifyDataSetChanged()
        }
        datePicker.show(parentFragmentManager, "MaterialDatePicker")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@SuppressLint("SimpleDateFormat")
fun Long.convertToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}
