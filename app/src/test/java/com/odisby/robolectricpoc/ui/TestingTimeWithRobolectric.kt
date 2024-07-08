package com.odisby.robolectricpoc.ui

import com.odisby.robolectricpoc.testutils.BaseRobot
import com.odisby.robolectricpoc.testutils.RUN_UNIT_TEST
import com.odisby.robolectricpoc.testutils.THEN
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.system.measureTimeMillis

@RunWith(RobolectricTestRunner::class)
class TestingTimeWithRobolectric {
    private val robot = TestingTimeWithRobolectricRobot()

    @Before
    fun setup() {
        robot.setup()
    }

    @Test
    fun addition_asset() {
        RUN_UNIT_TEST(robot) {
            THEN { assertMath(math = { 2 + 2}, result = 4) }
        }
    }

    @Test
    fun sortingTest() {
        RUN_UNIT_TEST(robot) {
            THEN { sortingTest() }
        }
    }

    @Test
    fun testFibonacci() {
        RUN_UNIT_TEST(robot) {
            THEN { testFibonacci() }
        }
    }


    private class TestingTimeWithRobolectricRobot: BaseRobot() {
        fun assertMath(math: () -> Int, result: Int) {
            val mathResult = math()
            assert(mathResult == result)
        }

        fun sortingTest() {
            val list = listOf(5, 3, 2, 8, 1, 4)
            val sortedList = list.sorted()
            assertEquals(listOf(1, 2, 3, 4, 5, 8), sortedList)
        }

        fun testFibonacci() {
            fun fibonacci(n: Int): Int {
                return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
            }
            assertEquals(55, fibonacci(10))
        }
    }
}