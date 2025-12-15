package com.aradsheybak.goodfood.data.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.Flow
import androidx.compose.runtime.State

@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
    initial: T,
    context: android.content.Context = LocalContext.current
): State<T> {
    return this.collectAsState(initial = initial)
}
@Composable
fun rememberPreferencesManager(): PreferencesManager {
    val context = LocalContext.current
    return androidx.compose.runtime.remember { PreferencesManager(context) }
}

@Composable
fun PreferencesManager.collectDarkModeState(): State<Boolean> {
    return isDarkMode.collectAsStateWithLifecycle(initial = false)
}
