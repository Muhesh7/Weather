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
package com.example.androiddevchallenge.view.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.androiddevchallenge.model.Coordinates
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

@SuppressLint("MissingPermission")
@Composable
fun MapView(
    mapView: MapView,
    location: Coordinates,
    modifier: Modifier,
    viewModel: WeatherViewModel
) {
    val position = remember { mutableStateOf(LatLng(location.lat, location.lon)) }
    AndroidView({ mapView }, modifier = modifier) { mapping ->
        mapping.getMapAsync() { map ->
            position.value = LatLng(location.lat, location.lon)
            map.addMarker(
                MarkerOptions().position(position.value).draggable(true)
            )
            map.uiSettings.isZoomControlsEnabled = true
            map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragStart(p0: Marker?) {
                }

                override fun onMarkerDrag(p0: Marker?) {
                }

                override fun onMarkerDragEnd(p0: Marker?) {
                    position.value = p0!!.position
                    viewModel.getWeather(position.value.longitude, position.value.latitude)
                }
            })
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position.value, 15.0f))

            val center: CameraUpdate = CameraUpdateFactory.newLatLng(position.value)
            val zoom: CameraUpdate = CameraUpdateFactory.zoomTo(15F)
            map.isMyLocationEnabled = false
            map.uiSettings.isMyLocationButtonEnabled = false
            map.moveCamera(center)
            map.animateCamera(zoom)
            map.setMaxZoomPreference(15F)
            map.setMinZoomPreference(1F)
            map.uiSettings?.isZoomControlsEnabled = true
            map.uiSettings?.setAllGesturesEnabled(true)
        }
    }
}
