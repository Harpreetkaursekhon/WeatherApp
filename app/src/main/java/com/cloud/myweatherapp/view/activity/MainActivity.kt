package com.cloud.myweatherapp.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.cloud.myweatherapp.R
import com.cloud.myweatherapp.mymodel.Data
import com.cloud.myweatherapp.view.adapter.CountryListAdapter
import com.cloud.myweatherapp.view.adapter.ItemListDayAdapter
import com.cloud.myweatherapp.view.adapter.SectionsPagerAdapter
import com.cloud.myweatherapp.view.fragment.CurrentFragment
import com.cloud.myweatherapp.view.fragment.DateFragment
import com.cloud.myweatherapp.view.fragment.DaysFragment
import com.cloud.myweatherapp.viewmodel.AFViewModel
import com.cloud.myweatherapp.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_countries_list.*
import java.util.*

class MainActivity : AppCompatActivity(), CountryListAdapter.CellClickListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private const val REQUEST_CHECK_SETTINGS = 199
    }

    private val countryListAdapter: CountryListAdapter = CountryListAdapter()
    private lateinit var viewModel: SharedViewModel
    private var countryList: ArrayList<String> = arrayListOf()

    private lateinit var afviewModel: AFViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        setupViewPager(viewPager)

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val ll_tv: LinearLayout = findViewById(R.id.ll_tv)
        ll_tv.setOnClickListener {
            showHide(ll_fiter_list, appBarLayout, ll_tv)
        }

        ic_close.setOnClickListener {
            showHide(ll_fiter_list, appBarLayout, ll_tv)
        }

        //  viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        viewModel.selected.observe(this, Observer<List<Data>> { item ->

            //  val hashSet = HashSet<String>()

            for (i in item.indices) {
                if (!countryList.contains(item[i]._country._name))
                    countryList.add(item[i]._country._name)
            }

            countryList.sortWith(Comparator { obj: String, s: String? ->
                obj.compareTo(
                    s!!, ignoreCase = true
                )
            })

        })

        afviewModel = this.run {
            ViewModelProviders.of(this)[AFViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }

    private fun showHide(ll_fiter_list: View, appBarLayout: View, ll_tv: View) {

        recycler_view_country.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        appBarLayout.visibility = if (appBarLayout.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }

        ll_fiter_list.visibility = if (ll_fiter_list.visibility == View.VISIBLE) {
            View.GONE
        } else {
            countryListAdapter.updateCountryList(countryList, this)
            View.VISIBLE
        }

        ll_tv.visibility = if (ll_tv.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionsPagerAdapter(supportFragmentManager)
        adapter.addFragment(DaysFragment(), "A-Z")
        adapter.addFragment(CurrentFragment(), "Temperature")
        adapter.addFragment(DateFragment(), "Last Updated")
        //   adapter.addFragment(SettingFragment(), "Setting")
        viewPager.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onCellClickListener(selectedCountry: String) {
        showHide(ll_fiter_list, appBarLayout, ll_tv)
        val fragment=DaysFragment.newInstance(selectedCountry)
        val fragment2=CurrentFragment.newInstance(selectedCountry)
        val fragment3=DateFragment.newInstance(selectedCountry)
   }

}