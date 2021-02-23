package com.cloud.myweatherapp.mymodel

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

import _country
import _sport
import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("_venueID") val _venueID : Int,
	@SerializedName("_name") val _name : String,
	@SerializedName("_country") val _country : _country,
	@SerializedName("_weatherCondition") val _weatherCondition : String,
	@SerializedName("_weatherConditionIcon") val _weatherConditionIcon : String,
	@SerializedName("_weatherWind") val _weatherWind : String,
	@SerializedName("_weatherHumidity") val _weatherHumidity : String,
	@SerializedName("_weatherTemp") val _weatherTemp : Int,
	@SerializedName("_weatherFeelsLike") val _weatherFeelsLike : Int,
	@SerializedName("_sport") val _sport : _sport,
	@SerializedName("_weatherLastUpdated") val _weatherLastUpdated : Int
)