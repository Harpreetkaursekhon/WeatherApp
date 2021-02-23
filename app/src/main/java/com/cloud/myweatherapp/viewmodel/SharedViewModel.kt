package com.cloud.myweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloud.myweatherapp.mymodel.Data

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<List<Data>>()

    fun selectedItem(item: List<Data>) {
        selected.value = item
    }
}