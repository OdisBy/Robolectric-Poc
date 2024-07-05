package com.odisby.robolectricpoc


import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnit4
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testAdditionTime() {
        val time = measureTimeMillis {
            assertEquals(4, 2 + 2)
        }
        println("Time for addition test: $time ms")
    }

    @Test
    fun testMultiplicationTime() {
        val time = measureTimeMillis {
            assertEquals(9, 3 * 3)
        }
        println("Time for multiplication test: $time ms")
    }

    @Test
    fun testStringConcatenationTime() {
        val time = measureTimeMillis {
            assertEquals("Hello, World!", "Hello, " + "World!")
        }
        println("Time for string concatenation test: $time ms")
    }

    @Test
    fun testListSortingTime() {
        val list = listOf(5, 3, 2, 8, 1, 4)
        val time = measureTimeMillis {
            val sortedList = list.sorted()
            assertEquals(listOf(1, 2, 3, 4, 5, 8), sortedList)
        }
        println("Time for list sorting test: $time ms")
    }

    @Test
    fun testMapAccessTime() {
        val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        val time = measureTimeMillis {
            assertEquals("value2", map["key2"])
        }
        println("Time for map access test: $time ms")
    }

    @Test
    fun testFibonacciTime() {
        fun fibonacci(n: Int): Int {
            return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
        }
        val time = measureTimeMillis {
            assertEquals(55, fibonacci(10))
        }
        println("Time for Fibonacci test: $time ms")
    }

    @Test
    fun testFactorialTime() {
        fun factorial(n: Int): Int {
            return if (n == 0) 1 else n * factorial(n - 1)
        }
        val time = measureTimeMillis {
            assertEquals(120, factorial(5))
        }
        println("Time for factorial test: $time ms")
    }
}