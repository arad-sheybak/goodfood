package com.aradsheybak.goodfood.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {

    val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

    val SHOW_ONBOARDING = booleanPreferencesKey("show_onboarding")
    val IS_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")

    val USER_TOKEN = stringPreferencesKey("user_token")

    val USER_NAME = stringPreferencesKey("user_name")
    val USER_EMAIL = stringPreferencesKey("user_email")

}