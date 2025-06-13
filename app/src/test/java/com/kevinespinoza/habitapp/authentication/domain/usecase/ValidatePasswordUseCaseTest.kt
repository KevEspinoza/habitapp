package com.kevinespinoza.habitapp.authentication.domain.usecase

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setup(){
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `given low character password, return invalid password`() {
        val input = "asd"
        val result = validatePasswordUseCase(input)

        assertEquals(PasswordResult.Invalid("Password must be at least 8 characters long"), result)
    }


}