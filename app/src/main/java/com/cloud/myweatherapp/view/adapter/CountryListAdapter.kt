package com.cloud.myweatherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloud.myweatherapp.R
import kotlinx.android.synthetic.main.item_country_name.*
import kotlinx.android.synthetic.main.item_country_name.view.*
import kotlinx.android.synthetic.main.item_layout_day.view.*


class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.ItemListDayVH>() {

    private var items: ArrayList<String> = arrayListOf()
    lateinit var cellClickListener: CellClickListener

    interface CellClickListener {
        fun onCellClickListener(a:String)
    }

    fun updateCountryList(items: List<String>,cellClickListener: CellClickListener) {
        this.cellClickListener = cellClickListener
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemListDayVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country_name, parent, false)
    )

    override fun onBindViewHolder(holder: ItemListDayVH, position: Int) {
        holder.bind(items[position],cellClickListener)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemListDayVH(view: View) : RecyclerView.ViewHolder(view) {
        private var tv_country: TextView = view.tv_country

        fun bind(item: String,cellClickListener:CellClickListener) {
            val temp = item
            tv_country.text = "$temp"

            tv_country.setOnClickListener {
                cellClickListener.onCellClickListener(item)
            }


        }
    }
}