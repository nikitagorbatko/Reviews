package nikitagorbatko.fojin.test.reviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import nikitagorbatko.fojin.test.reviews.databinding.ActivityMainBinding
import nikitagorbatko.fojin.test.reviews.ui.main.PagerAdapter
import nikitagorbatko.fojin.test.reviews.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout: TabLayout = binding.tabLayout
        val viewPager = binding.pager
        val pagerAdapter = PagerAdapter(this)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) { "Рецензии" } else { "Критики" }
        }.attach()
    }
}