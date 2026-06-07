package com.example.myapplication.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val GameColorScheme = darkColorScheme(
    primary = GameBlue,
    onPrimary = Color.Black,
    primaryContainer = GamePurple,
    onPrimaryContainer = Color.White,
    secondary = GameGold,
    onSecondary = Color.Black,
    tertiary = GameNeon,
    background = GameDark,
    surface = GameSurface,
    onSurface = GameOnSurface,
    error = Color(0xFFFF5252)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
    }

    MaterialTheme(
        colorScheme = GameColorScheme,
        typography = Typography,
        content = content
    )
}
