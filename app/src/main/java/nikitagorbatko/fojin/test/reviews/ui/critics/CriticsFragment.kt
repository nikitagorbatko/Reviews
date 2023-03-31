package nikitagorbatko.fojin.test.reviews.ui.critics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nikitagorbatko.fojin.test.reviews.databinding.FragmentCriticsBinding

class CriticsFragment : Fragment() {
    private lateinit var pageViewModel: CriticsViewModel

    private var _binding: FragmentCriticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(CriticsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCritics.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}