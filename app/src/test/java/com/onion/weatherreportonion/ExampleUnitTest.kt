package com.onion.weatherreportonion

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun to_string_isCorrect() {
        val isoTime = "2026-01-09T23:00"
        val substr = isoTime.substring(11, 13)

        assertEquals(23, substr.toInt())
    }
}



