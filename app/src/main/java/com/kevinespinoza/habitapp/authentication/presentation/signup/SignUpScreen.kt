package com.kevinespinoza.habitapp.authentication.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevinespinoza.habitapp.R
import com.kevinespinoza.habitapp.authentication.presentation.signup.components.SignUpForm

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onLogIn: () -> Unit,
    onSignIn: () -> Unit,
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(state.toastError) {
        if (state.toastError != null) {
            Toast.makeText(context, state.toastError, Toast.LENGTH_SHORT).show()
            viewModel.stopToast()
        }
    }

    LaunchedEffect(state.isSingedIn) {
        if(state.isSingedIn){
            onSignIn()
        }
    }

    LaunchedEffect(state.logIn) {
        if(state.logIn){
            onLogIn()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(painter = painterResource(id = R.drawable.signup), contentDescription = "Sign Up")
        SignUpForm(state, viewModel::onEvent, modifier = Modifier.fillMaxWidth())
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}