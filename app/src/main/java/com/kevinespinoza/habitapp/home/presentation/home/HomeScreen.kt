package com.kevinespinoza.habitapp.home.presentation.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevinespinoza.habitapp.R
import com.kevinespinoza.habitapp.home.presentation.home.components.HomeAskPermission
import com.kevinespinoza.habitapp.home.presentation.home.components.HomeDateSelector
import com.kevinespinoza.habitapp.home.presentation.home.components.HomeHabit
import com.kevinespinoza.habitapp.home.presentation.home.components.HomeQuote
import com.kevinespinoza.habitapp.ui.components.CustomTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNewHabit: () -> Unit,
    onSettings: () -> Unit,
    onEditHabit: (String) -> Unit,
) {
    val state = viewModel.state
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize(), topBar = {
            CustomTopBar(
                title = {
                    Text(
                        text = "Home"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onSettings() }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNewHabit()
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Habit",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            HomeAskPermission(
                permission = Manifest.permission.POST_NOTIFICATIONS
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
        ) {
            item {
                HomeQuote(
                    quote = "We first make our habits, and then our habits makes us.",
                    author = "Anonymous",
                    imageId = R.drawable.onboarding1,
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Habits".uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                    HomeDateSelector(
                        selectorDate = state.selectedDate,
                        mainDate = state.currentDate,
                        onDateClick = { zoneData ->
                            viewModel.onEvent(HomeEvent.ChangeDate(zoneData))
                        }
                    )
                }
            }
            items(state.habits) { item ->
                HomeHabit(
                    habit = item,
                    selectedDate = state.selectedDate.toLocalDate(),
                    onCheckedChange = { viewModel.onEvent(HomeEvent.CompleteHabit(item)) },
                    onHabitClick = { onEditHabit(item.id) },
                )
            }
        }
    }
}