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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.util.Constants
import com.example.androiddevchallenge.util.glider
import com.example.androiddevchallenge.view.MainActivity
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import com.google.android.gms.maps.MapView

@Composable
fun App(viewModel: WeatherViewModel, activity: MainActivity, mapView: MapView) {

    Scaffold {
        if (viewModel.isLoaded.value) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (card, bar) = createRefs()
                Column(
                    modifier = Modifier
                        .constrainAs(card) {
                            bottom.linkTo(bar.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                ) {
                    MapView(
                        mapView,
                        location = viewModel.response.value.coord,
                        modifier = Modifier
                            .weight(0.35f),
                        viewModel = viewModel
                    )
                    Surface(
                        elevation = 20.dp,
                        modifier = Modifier
                            .weight(0.65f)
                    ) {
                        val icon =
                            viewModel.response.value.weather[0].icon.dropLast(1) + Constants.ic
                        val image = glider(
                            "http://openweathermap.org/img/wn/$icon.png",
                            activity
                        )
                        Row(
                            Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                shape = shapes.medium,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(8.dp),
                                elevation = 8.dp,
                            ) {

                                DetailsCard(viewModel, image)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Card(
                                    shape = shapes.medium,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp),
                                    elevation = 8.dp
                                ) {
                                    TemperatureCard(viewModel)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Card(
                                    shape = shapes.medium,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp),
                                    elevation = 8.dp
                                ) {
                                    LocationCard(viewModel)
                                }
                            }
                        }
                    }
                    BottomBar(viewModel, activity)
                }
            }
        } else {
            SplashScreen()
        }
    }
}
