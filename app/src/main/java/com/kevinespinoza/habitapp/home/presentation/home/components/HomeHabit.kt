package com.kevinespinoza.habitapp.home.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kevinespinoza.habitapp.core.presentation.HabitCheckbox
import com.kevinespinoza.habitapp.home.domain.models.Habit
import java.time.LocalDate

@Composable
fun HomeHabit(
    habit: Habit,
    selectedDate: LocalDate,
    onCheckedChange: () -> Unit,
    onHabitClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                MaterialTheme.colorScheme.background
            )
            .clickable {
                onHabitClick()
            }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = habit.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        HabitCheckbox(
            isChecked = habit.completedDates.contains(selectedDate)
        ) {
            onCheckedChange()
        }
    }
}