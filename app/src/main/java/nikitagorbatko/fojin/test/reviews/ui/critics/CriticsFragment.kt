package nikitagorbatko.fojin.test.reviews.ui.critics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.R
import nikitagorbatko.fojin.test.reviews.api.ResultDto
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.data.CriticsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.databinding.FragmentCriticsBinding
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsUseCase

class CriticsFragment : Fragment() {
    private var _binding: FragmentCriticsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CriticsViewModel> {
        val retrofit = RetrofitReviews
        val criticsRepository = CriticsRepositoryImpl.getInstance(retrofit)
        val getCriticsUseCase = GetCriticsUseCase(criticsRepository)
        CriticsViewModel.Companion.CriticsViewModelFactory(getCriticsUseCase)
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
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ResultDto> = moshi.adapter(ResultDto::class.java)
        val adapter = CriticsAdapter {
            val bundle = bundleOf("critic" to jsonAdapter.toJson(it))
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.visibility = View.GONE
            findNavController().navigate(R.id.action_criticsFragment_to_singleCriticFragment, bundle)
        }
        binding.recyclerCritics.adapter = adapter
        binding.recyclerCritics.addItemDecoration(SpacesItemDecoration(16))

        viewModel.viewModelScope.launch {
            viewModel.critics.collect {
                adapter.add(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}