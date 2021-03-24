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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.view.MainActivity
import com.example.androiddevchallenge.viewmodel.WeatherViewModel

@Composable
fun BottomBar(viewModel: WeatherViewModel, activity: MainActivity) {
    BottomAppBar(
        modifier = Modifier
            .height(50.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.onSecondary

    ) {
        IconButton(
            onClick = {
                viewModel.setMode()
            },
            Modifier.weight(1f)
        ) {
            Icon(
                Icons.Filled.DarkMode, "darkMode",
                Modifier
                    .padding(15.dp)
            )
        }
        IconButton(
            onClick = {
                viewModel.voice()
            },
            Modifier.weight(1f)
        ) {
            Icon(
                Icons.Filled.Mic, "mic",
                Modifier
                    .background(MaterialTheme.colors.onBackground, CircleShape)
                    .padding(15.dp)
            )
        }
        IconButton(
            onClick = {
                viewModel.getLocation(activity)
            },
            Modifier.weight(1f)
        ) {
            Icon(
                Icons.Filled.LocationSearching, "Current Location",
                Modifier
                    .padding(15.dp)
            )
        }
    }
}
