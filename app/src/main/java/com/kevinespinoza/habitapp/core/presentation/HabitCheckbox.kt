package com.kevinespinoza.habitapp.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HabitCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: () -> Unit,
) {
    val backgroundColor =
        if (isChecked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = backgroundColor)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onCheckedChange()
            },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Check,
                contentDescription = "checked",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
private fun HabitCheckboxCheckedPreview() {
    HabitCheckbox(
        isChecked = true,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun HabitCheckboxUncheckedPreview() {
    HabitCheckbox(
        isChecked = false,
        onCheckedChange = {}
    )
}