package com.cloud.myweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloud.myweatherapp.mymodel.Data

class AFViewModel : ViewModel() {
    val selected = MutableLiveData<String>()

    fun selectedItem(item: String) {
        selected.value = item
    }
}