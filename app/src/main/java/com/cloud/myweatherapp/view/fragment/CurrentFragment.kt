package com.cloud.myweatherapp.view.fragment

import Json4Kotlin_Base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.myweatherapp.R
import com.cloud.myweatherapp.mymodel.Data
import com.cloud.myweatherapp.service.MyWeatherServiceBuilder
import com.cloud.myweatherapp.service.WeatherService
import com.cloud.myweatherapp.util.PREF_USER_NAME
import com.cloud.myweatherapp.util.getUserDetails
import com.cloud.myweatherapp.view.adapter.ItemListDayAdapter
import com.cloud.myweatherapp.viewmodel.AFViewModel
import com.cloud.myweatherapp.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_days.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

public class CurrentFragment : Fragment() {

    companion object {
        private val TAG = CurrentFragment::class.java.simpleName
        const val ARG_NAME= "name"
        private var countryList: List<Data> = arrayListOf()
        private val itemListDayAdapter: ItemListDayAdapter = ItemListDayAdapter()

        fun newInstance(name: String): CurrentFragment{
            val fragment =CurrentFragment()
            val bundle =Bundle().apply{
                putString(ARG_NAME, name)
            }
            fragment.arguments=bundle
            fragment.updateAF()

            return fragment
        }

    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var afviewModel: AFViewModel


    private lateinit var textView: TextView

    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_days, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView = view.days_tv_usr_name

        view.recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemListDayAdapter
        }

        view.days_refresh.setOnClickListener {
            updateUI()
        }

        afviewModel = ViewModelProvider(this)[AFViewModel::class.java]

        afviewModel.selected.observe(viewLifecycleOwner, Observer<String> { item ->
        })
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val cd = sdf.format(Date())
        textView.text = "Last Updated:  \n ${cd}"

        count = 0
        if (count == 0) {
            fetchCurrentWeather()
            count++
        }
    }

    private fun fetchCurrentWeather() {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val cd = sdf.format(Date())
        textView.text = "Last Updated:  \n ${cd}"

        val weatherService = MyWeatherServiceBuilder.buildService(WeatherService::class.java)
        val response = weatherService.getVenues()

        response.enqueue(object : Callback<Json4Kotlin_Base> {
            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                if (response.isSuccessful) {
                    countryList = response.body()!!.data
                    val sortedList = countryList.sortedWith(
                        compareBy(
                            Data::_weatherTemp,
                            Data::_weatherTemp
                        )
                    )
                    Collections.reverse(sortedList)

                    itemListDayAdapter.updateItemListDay(sortedList)
                    viewModel.selectedItem(response.body()!!.data)
//                    textView.text = "UserName: ${context?.getUserDetails(PREF_USER_NAME)}\nPlace: ${response.body()?.city?.name}"
                }
            }
        })
    }

    public fun updateAF() {
        val name=arguments?.getString(ARG_NAME)

        //
        val tempList = ArrayList<Data>()

        for (i in countryList.indices) {
            // hashSet.add(item.get(i)._country.get_name());
            if (countryList[i]._country._name == name) {
                tempList.add(countryList[i])
            }
        }

        val sortedList = tempList.sortedWith(compareBy(Data::_weatherTemp, Data::_weatherTemp))
        Collections.reverse(sortedList)


        itemListDayAdapter.updateItemListDay(sortedList)
        itemListDayAdapter.notifyDataSetChanged()

    }
}