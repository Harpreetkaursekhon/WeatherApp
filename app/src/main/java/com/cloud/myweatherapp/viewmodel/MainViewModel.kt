package com.cloud.myweatherapp.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

}
