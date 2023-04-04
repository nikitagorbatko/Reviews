package nikitagorbatko.fojin.test.reviews.ui.critics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.R
import nikitagorbatko.fojin.test.reviews.api.CriticDto
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.data.CriticsDbRepositoryImpl
import nikitagorbatko.fojin.test.reviews.data.CriticsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.database.ReviewsDatabase
import nikitagorbatko.fojin.test.reviews.databinding.FragmentCriticsBinding
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsUseCase
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi

class CriticsFragment : Fragment() {
    private var _binding: FragmentCriticsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CriticsViewModel> {
        val criticsDao = ReviewsDatabase.getDatabase(requireContext()).getCriticsDao()
        val retrofit = RetrofitReviews
        val criticsRepository = CriticsRepositoryImpl.getInstance(retrofit, criticsDao)
        val criticsDbRepository = CriticsDbRepositoryImpl.getInstance(criticsDao)
        val getCriticsUseCase = GetCriticsUseCase(criticsRepository)
        val getCriticsDbUseCase = GetCriticsDbUseCase(criticsDbRepository)
        CriticsViewModel.Companion.CriticsViewModelFactory(getCriticsUseCase, getCriticsDbUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.visibility = View.VISIBLE
        _binding = FragmentCriticsBinding.inflate(inflater, container, false)
//        val activity
//        val params = appBar.layoutParams as CoordinatorLayout.LayoutParams
//        if (params.behavior == null)
//            params.behavior = AppBarLayout.Behavior()
//        val behaviour = params.behavior as AppBarLayout.Behavior
//        behaviour.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
//            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
//                return false
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<CriticUi> = moshi.adapter(CriticUi::class.java)
        val adapter = CriticsAdapter {
            val bundle = bundleOf("critic" to jsonAdapter.toJson(it))
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.visibility = View.GONE
            findNavController().navigate(R.id.action_criticsFragment_to_singleCriticFragment, bundle)
        }
        binding.recyclerCritics.adapter = adapter
        binding.recyclerCritics.addItemDecoration(SpacesItemDecoration(16))

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.critics.collect {
                    adapter.add(it)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}