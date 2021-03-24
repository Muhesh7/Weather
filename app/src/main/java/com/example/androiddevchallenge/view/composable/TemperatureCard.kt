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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.util.Constants
import com.example.androiddevchallenge.viewmodel.WeatherViewModel

@Composable
fun TemperatureCard(viewModel: WeatherViewModel) {
    Column(
        modifier = Modifier
            .background(Brush.linearGradient(Constants.lc))
            .absolutePadding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Temp",
            color = MaterialTheme.colors.onSecondary,
            style = typography.caption,
        )
        Text(
            text = (viewModel.response.value.main.temp - 275.15).toInt()
                .toString() + "°C",
            color = MaterialTheme.colors.onSecondary,
            style = typography.h5,
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "Min/Max",
            color = MaterialTheme.colors.onSecondary,
            style = typography.caption,
        )
        Row(
            Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = (viewModel.response.value.main.temp_min - 275.15).toInt()
                    .toString() + "°C",
                color = MaterialTheme.colors.onSecondary,
                style = typography.subtitle2,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "/",
                color = MaterialTheme.colors.onSecondary,
                style = typography.subtitle2,
                modifier = Modifier.padding(
                    horizontal = 2.dp,
                    vertical = 8.dp
                )
            )
            Text(
                text = (viewModel.response.value.main.temp_max - 275.15).toInt()
                    .toString() + "°C",
                color = MaterialTheme.colors.onSecondary,
                style = typography.subtitle2,
                modifier = Modifier.padding(8.dp)
            )
        }
        Divider(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onPrimary
        )
    }
}
