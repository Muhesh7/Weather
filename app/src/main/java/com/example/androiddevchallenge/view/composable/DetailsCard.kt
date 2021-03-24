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

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.util.Constants
import com.example.androiddevchallenge.viewmodel.WeatherViewModel
import java.util.Locale
import kotlin.math.ceil

@Composable
fun DetailsCard(viewModel: WeatherViewModel, image: MutableState<Bitmap>) {
    Column(
        Modifier
            .background(Brush.linearGradient(Constants.lc))
            .absolutePadding(12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Text(
                text = "Currently",
                style = typography.caption,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )
            Image(
                bitmap = image.value.asImageBitmap(),
                "image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(48.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
        Text(
            text = viewModel.response.value.weather[0].description.capitalize(Locale.getDefault()),
            style = typography.h4,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "Wind",
            color = MaterialTheme.colors.onSecondary,
            style = typography.caption,
        )
        Text(
            text = ceil(viewModel.response.value.wind.speed).toInt()
                .toString() + "km/h",
            style = typography.subtitle2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Humidity",
            color = MaterialTheme.colors.onSecondary,
            style = typography.caption,
        )
        Text(
            text = ceil(viewModel.response.value.main.humidity).toInt()
                .toString() + "%",
            color = MaterialTheme.colors.onSecondary,
            style = typography.subtitle2,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Pressure",
            color = MaterialTheme.colors.onSecondary,
            style = typography.caption,
        )
        Text(
            text = ceil(viewModel.response.value.main.pressure).toInt()
                .toString() + "hPa",
            color = MaterialTheme.colors.onSecondary,
            style = typography.subtitle2,
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.onPrimary
        )
    }
}
