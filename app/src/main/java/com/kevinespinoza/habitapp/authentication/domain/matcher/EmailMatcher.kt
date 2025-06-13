package com.kevinespinoza.habitapp.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}