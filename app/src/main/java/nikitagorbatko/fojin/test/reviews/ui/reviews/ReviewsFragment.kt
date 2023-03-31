package nikitagorbatko.fojin.test.reviews.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nikitagorbatko.fojin.test.reviews.databinding.FragmentMainBinding
import nikitagorbatko.fojin.test.reviews.databinding.FragmentReviewsBinding

/**
 * A placeholder fragment containing a simple view.
 */
class ReviewsFragment : Fragment() {
    private lateinit var pageViewModel: ReviewsViewModel
    
    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(ReviewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}