package com.kevinespinoza.habitapp.home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.ZonedDateTime

@Composable
fun HomeDateSelector(
    modifier: Modifier = Modifier,
    selectorDate: ZonedDateTime,
    mainDate: ZonedDateTime,
    onDateClick: (ZonedDateTime) -> Unit,
    datesToShow: Int = 4,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        for(i in datesToShow downTo 0){
            val date = mainDate.minusDays(i.toLong())
            HomeDateItem(
                date = date,
                isSelected = selectorDate.toLocalDate() == date.toLocalDate(),
                onClick = { onDateClick(date) },
            )
        }
    }
}