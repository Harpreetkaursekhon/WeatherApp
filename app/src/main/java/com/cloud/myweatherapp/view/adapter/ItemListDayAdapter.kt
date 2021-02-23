package com.cloud.myweatherapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloud.myweatherapp.DetailActivity
import com.cloud.myweatherapp.R
import com.cloud.myweatherapp.mymodel.Data
import kotlinx.android.synthetic.main.item_layout_day.view.*


class ItemListDayAdapter : RecyclerView.Adapter<ItemListDayAdapter.ItemListDayVH>() {

    private var items: ArrayList<Data> = arrayListOf()
//    public lateinit var myCellClickListener: ItemListDayAdapter.MyCellClickListener
//
//
//    interface MyCellClickListener {
//        fun onmyCellClickListener(value: String)
//    }


    fun updateItemListDay(
        items: List<Data>
    ) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemListDayVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_layout_day, parent, false)
    )

    override fun onBindViewHolder(holder: ItemListDayVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemListDayVH(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView = view.day_tv_details
        private var tv_weather_type: TextView = view.tv_weather_type
        private var tv_degree: TextView = view.tv_degree
        private var rl_weather: RelativeLayout = view.rl_weather

        fun bind(item: Data) {
            val weather = item._weatherCondition
            val temp = item._weatherTemp
            val suburb = item._name

            textView.text = "$suburb "
            tv_weather_type.text = "$weather "
            tv_degree.text = "$temp°"

                rl_weather.setOnClickListener(View.OnClickListener { v ->
                    v.context.startActivity(

                            Intent(
                            v.context,
                            DetailActivity::class.java
                        ).putExtra("name", item._name)
                            .putExtra("temp", item._weatherTemp.toString())
                            .putExtra("feels", item._weatherFeelsLike.toString())
                            .putExtra("humidity", item._weatherHumidity)
                            .putExtra("wind", item._weatherWind)

                    )
                })


        }
    }
}