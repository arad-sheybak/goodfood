package com.aradsheybak.goodfood.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "goodfood_datastore")

class PreferencesManager (private val context: Context){

    val isFirstLaunch: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[DataStoreKeys.IS_FIRST_LAUNCH] ?: true
        }

    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.IS_DARK_MODE] ?: false
        }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.IS_LOGGED_IN] ?: false
        }


    suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_FIRST_LAUNCH] = isFirstLaunch
        }
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setUserLoggedIn(isLoggedIn: Boolean, token: String = "") {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_LOGGED_IN] = isLoggedIn
            if (token.isNotEmpty()) {
                preferences[DataStoreKeys.USER_TOKEN] = token
            }
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.remove(DataStoreKeys.USER_TOKEN)
            preferences.remove(DataStoreKeys.USER_NAME)
            preferences.remove(DataStoreKeys.USER_EMAIL)
            preferences[DataStoreKeys.IS_LOGGED_IN] = false
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}