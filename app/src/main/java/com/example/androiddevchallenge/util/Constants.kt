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
package com.example.androiddevchallenge.util

import com.example.androiddevchallenge.model.ApiResponse
import com.example.androiddevchallenge.model.Coordinates
import com.example.androiddevchallenge.model.Main
import com.example.androiddevchallenge.model.Weather
import com.example.androiddevchallenge.model.Wind
import com.example.androiddevchallenge.ui.theme.ntone1
import com.example.androiddevchallenge.ui.theme.ntone2
import com.example.androiddevchallenge.ui.theme.ntone3

object Constants {
    val responseDummy = ApiResponse(
        Coordinates(0.0, 0.0),
        "",
        0,
        Main(0.0, 0.0, 0.0, 0.0, 0.0),
        Wind(0.0, 0.0),
        arrayListOf(Weather("Normal", ""))
    )

    var ic = "d"
    var lc = listOf(ntone1, ntone2, ntone3)
}
