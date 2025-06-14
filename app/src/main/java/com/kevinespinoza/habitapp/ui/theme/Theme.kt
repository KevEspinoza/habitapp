package com.kevinespinoza.habitapp.ui.theme

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    background = Background,
    surface = Accent,
    error = Error,
)

@Suppress("DEPRECATION")
@Composable
fun HabitAppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                (view.context as Activity).window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                    val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                    view.setBackgroundColor(colorScheme.tertiary.toArgb())
                    view.setPadding(0, statusBarInsets.top, 0, 0)
                    insets
                }
            } else {
                (view.context as Activity).window.statusBarColor = colorScheme.tertiary.toArgb()
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}