package com.kevinespinoza.habitapp.authentication.data.matcher

import android.util.Patterns
import com.kevinespinoza.habitapp.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl: EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}