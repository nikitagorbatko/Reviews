package nikitagorbatko.fojin.test.reviews.ui.main

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import nikitagorbatko.fojin.test.reviews.ui.critics.CriticsFragment
import nikitagorbatko.fojin.test.reviews.ui.reviews.ReviewsFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = if (position == 0) {
        ReviewsFragment()
    } else {
        CriticsFragment()
    }
}