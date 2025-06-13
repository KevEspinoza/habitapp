package com.kevinespinoza.habitapp.core.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HabitTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(text = title.uppercase(),
        modifier = modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary
        ),
        textAlign = TextAlign.Center
    )
}