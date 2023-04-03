package nikitagorbatko.fojin.test.reviews.ui.single_critic

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import nikitagorbatko.fojin.test.reviews.api.ResultDto
import nikitagorbatko.fojin.test.reviews.databinding.FragmentSingleCriticBinding
import okio.BufferedSink


class SingleCriticFragment : Fragment() {
    private var _binding: FragmentSingleCriticBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModels<ReviewsViewModel> {
//        ReviewsViewModel.Companion.ReviewsViewModelFactory()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCriticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ResultDto> = moshi.adapter(ResultDto::class.java)
        val a = arguments?.getString("critic")?.let { jsonAdapter.fromJson(it) }
        binding.textViewError.text = a?.displayName
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}