package com.kevinespinoza.habitapp.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.kevinespinoza.habitapp.authentication.presentation.signup.SignUpEvent
import com.kevinespinoza.habitapp.authentication.presentation.signup.SignUpState
import com.kevinespinoza.habitapp.core.presentation.HabitButton
import com.kevinespinoza.habitapp.core.presentation.HabitPasswordTextField
import com.kevinespinoza.habitapp.core.presentation.HabitTextField
import com.kevinespinoza.habitapp.core.presentation.HabitTitle

@Composable
fun SignUpForm(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HabitTitle(title = "Create your account")
        Spacer(modifier = Modifier.height(32.dp))
        HabitTextField(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.EmailChange(it)) },
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
            backgroundColor = Color.White,
        )
        HabitPasswordTextField(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.PasswordChange(it)) },
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
                    onEvent(SignUpEvent.SignUp)
                }
            ),
            backgroundColor = Color.White,
        )
        Spacer(modifier = Modifier.height(12.dp))
        HabitButton(
            text = "Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SignUpEvent.SignUp)
        }
        TextButton(onClick = { onEvent(SignUpEvent.LogIn) }) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign In")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}