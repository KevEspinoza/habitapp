package com.kevinespinoza.habitapp.authentication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevinespinoza.habitapp.R
import com.kevinespinoza.habitapp.authentication.presentation.login.components.LoginForm
import com.kevinespinoza.habitapp.core.presentation.HabitTitle

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(state.toastError) {
        if (state.toastError != null) {
            Toast.makeText(context, state.toastError, Toast.LENGTH_SHORT).show()
            viewModel.stopToast()
        }
    }

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLogin()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer(
                    scaleX = 1.27f,
                    scaleY = 1.27f
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HabitTitle(title = "Welcome to")
                HabitTitle(title = "Monumental Habits")
            }
            LoginForm(
                state = state,
                onEvent = viewModel::onEvent,
                onSignUp = onSignUp,
            )
        }
    }
}