package com.kevinespinoza.habitapp.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevinespinoza.habitapp.authentication.presentation.login.LoginEvent
import com.kevinespinoza.habitapp.authentication.presentation.login.LoginScreen
import com.kevinespinoza.habitapp.authentication.presentation.login.LoginStates
import com.kevinespinoza.habitapp.core.presentation.HabitButton
import com.kevinespinoza.habitapp.core.presentation.HabitPasswordTextField
import com.kevinespinoza.habitapp.core.presentation.HabitTextField
import com.kevinespinoza.habitapp.ui.theme.HabitAppTheme

@Composable
fun LoginForm(
    state: LoginStates,
    onEvent: (LoginEvent) -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier.background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login with Email",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            HabitTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.EmailChange(it)) },
                placeholder = "Email",
                contentDescription = "Enter email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                leadingIcon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
                errorMessage = state.emailError,
                isEnabled = !state.isLoading,
            )
            HabitPasswordTextField(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.PasswordChange(it)) },
                contentDescription = "Enter password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                errorMessage = state.passwordError,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        focusManager.clearFocus()
                        onEvent(LoginEvent.Login)
                    }
                ),
            )
            HabitButton(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                isEnabled = !state.isLoading
            ) {
                onEvent(LoginEvent.Login)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Forgot password?",
                    color = MaterialTheme.colorScheme.secondary,
                    textDecoration = TextDecoration.Underline
                )
            }
            TextButton(onClick = onSignUp) {
                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account? ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Sign up")
                        }
                    },
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

        }
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}

@Preview
@Composable
private fun LoginFormPreview() {
    HabitAppTheme {
        LoginScreen(
            onLogin = {},
            onSignUp = {},
        )
    }
}