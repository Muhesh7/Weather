/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender.SendIntentException
import android.location.Location
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.ApiResponse
import com.example.androiddevchallenge.model.Response
import com.example.androiddevchallenge.repositories.ApiRepository
import com.example.androiddevchallenge.repositories.PreferencesRepository
import com.example.androiddevchallenge.util.Constants.responseDummy
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val prefRepository: PreferencesRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    val response: MutableState<ApiResponse> = mutableStateOf(responseDummy)
    val isLoaded: MutableState<Boolean> = mutableStateOf(false)
    val error: MutableState<String> = mutableStateOf("")
    val isDarkMode: MutableState<Boolean> = mutableStateOf(false)
    lateinit var mTS: TextToSpeech

    init {
        getIsDarkMode()
    }

    private fun getIsDarkMode() {
        CoroutineScope(coroutineContext).launch {
            isDarkMode.value = prefRepository.getIsDark()
        }
    }

    fun setMode() {
        CoroutineScope(coroutineContext).launch {
            prefRepository.storeMode(!isDarkMode.value)
        }.invokeOnCompletion {
            getIsDarkMode()
        }
    }

    private fun speech(): String =
        "location: ${response.value.name}, climate: ${response.value.weather[0].description}, " +
            "Temperature: ${response.value.main.temp}degree celsius, Pressure: ${response.value.main.pressure}pascal," +
            "Humidity: ${response.value.main.humidity} percentage,wind speed:${response.value.wind.speed} kilo metre per hour"

    fun getWeather(lon: Double, lat: Double) {
        CoroutineScope(coroutineContext).launch {
            when (val resp = apiRepository.getWeather(lat, lon)) {
                is Response.Value -> {
                    response.value = resp.value
                    isLoaded.value = true
                    Log.d("HHH", resp.toString())
                }
                is Response.Error -> {
                    error.value = error.value
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(activity: Activity) {
        LocationServices.getSettingsClient(activity)
            .checkLocationSettings(
                LocationSettingsRequest.Builder().build()
            ).addOnSuccessListener {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { loc: Location? ->
                        if (loc != null) {
                            getWeather(loc.longitude, loc.latitude)
                        }
                    }.addOnFailureListener {
                        Log.d("HHH", it.toString())
                    }
            }.addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    try {
                        e.startResolutionForResult(
                            activity,
                            200
                        )
                    } catch (sendEx: SendIntentException) {
                        Log.d("HHH", sendEx.toString())
                    }
                }
            }
    }

    fun tTS(activity: Activity) {
        mTS = TextToSpeech(activity) {
            if (it == TextToSpeech.SUCCESS) {
                val res = mTS.setLanguage(Locale.ENGLISH)
            } else {
                Log.d("HHH", "Failure")
            }
        }
    }

    fun voice() {
        mTS.speak(
            speech(),
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        )
    }

    override fun onCleared() {
        super.onCleared()
        mTS.shutdown()
    }
}
