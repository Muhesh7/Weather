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
package com.example.androiddevchallenge.repositories

import com.example.androiddevchallenge.ApiInterface
import com.example.androiddevchallenge.BuildConfig
import com.example.androiddevchallenge.model.ApiResponse
import com.example.androiddevchallenge.model.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getWeather(lat: Double, lon: Double): Response<ApiResponse> = try {
        val resp = apiInterface.getWeather(lat, lon, BuildConfig.API_KEY)
        if (resp.cod == 200)
            Response.build { resp }
        else Response.build { throw Exception(resp.cod.toString()) }
    } catch (e: Exception) {
        Response.build { throw Exception(e.toString()) }
    }
}
