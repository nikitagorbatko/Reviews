package nikitagorbatko.fojin.test.reviews


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuItemCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import nikitagorbatko.fojin.test.reviews.databinding.ActivityMainBinding
import nikitagorbatko.fojin.test.reviews.ui.main.PagerAdapter


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
            tab.text = if (position == 0) {
                "Рецензии"
            } else {
                "Критики"
            }
        }.attach()
        setSupportActionBar(binding.toolbar2)

        binding.toolbar2.setOnClickListener { button ->
            val datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(getString(R.string.app_name))
                .build()

            datePicker.addOnPositiveButtonClickListener {
//                val snackbarMessage: String = if (currentTimeInMillis < it) {
//                    getString(R.string.birth_date_error)
//                } else {
//                    calendar.timeInMillis = it
//                    dateFormatter.format(calendar.time)
//                }

                Snackbar.make(button, "snackbarMessage", Snackbar.LENGTH_SHORT)
                    .show()
            }

            datePicker.show(supportFragmentManager, "MaterialDatePicker")
        }
    }


    //    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        menu?.clear()
//        menu?.add(0, 0, Menu.NONE, getString(R.string.app_name))?.setIcon(R.drawable.ic_person_circle)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
//        menu?.add(0, 1, Menu.NONE, getString(R.string.app_name))?.setIcon(R.drawable.ic_person_circle)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
//        return super.onPrepareOptionsMenu(menu)
//    }
//
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.abb_bar_menu, menu)

//        val searchViewItem = menu!!.findItem(R.id.search_bar)
//        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
//
//        // attach setOnQueryTextListener
//        // to search view defined above
//
//        // attach setOnQueryTextListener
//        // to search view defined above
//        searchView.setOnQueryTextListener(object : OnQueryTextListener {
//            // Override onQueryTextSubmit method which is call when submit query is searched
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // If the list contains the search query than filter the adapter
//                // using the filter method with the query as its argument
////                if (list.contains(query)) {
////                    adapter.getFilter().filter(query)
////                } else {
////                    // Search query not found in List View
////                    Toast.makeText(this@MainActivity, "Not found", Toast.LENGTH_LONG).show()
////                }
//                return false
//            }
//
//            // This method is overridden to filter the adapter according
//            // to a search query when the user is typing search
//            override fun onQueryTextChange(newText: String?): Boolean {
//                //adapter.getFilter().filter(newText)
//                return false
//            }
//        })
        return true
    }
}