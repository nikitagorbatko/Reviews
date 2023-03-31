package nikitagorbatko.fojin.test.reviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import nikitagorbatko.fojin.test.reviews.databinding.ActivityMainBinding
import nikitagorbatko.fojin.test.reviews.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}