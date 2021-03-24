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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.util.Constants.ic
import com.example.androiddevchallenge.util.Constants.lc

val DarkColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200,
    onSecondary = ntext,
    surface = nsurf,
    onSurface = nBar,
    onPrimary = nDiv,
    onBackground = nBut
)

val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200,
    onSecondary = dtext,
    surface = dsurf,
    onSurface = dBar,
    onPrimary = dDiv,
    onBackground = dBut
)

@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable() () -> Unit) {
    if (darkTheme) {
        ic = "d"
        lc = listOf(ntone1, ntone2, ntone3)
    } else {
        ic = "n"
        lc = listOf(dtone1, dtone2, dtone3)
    }
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = typography,
        shapes = shapes,
    ) {
        content()
    }
}
