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

import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.example.androiddevchallenge.WeatherApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(private val mContext: WeatherApplication) {

    private val dataStore = mContext.createDataStore(name = "DarkMode")

    object PreferenceKey {
        val modeKey = preferencesKey<Boolean>("mode")
    }

    private val modeFlow: Flow<Boolean> = dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[PreferenceKey.modeKey] ?: false
    }

    suspend fun getIsDark(): Boolean {
        return modeFlow.first()
    }

    suspend fun storeMode(isDark: Boolean) {
        dataStore.edit {
            it[PreferenceKey.modeKey] = isDark
        }
    }
}
