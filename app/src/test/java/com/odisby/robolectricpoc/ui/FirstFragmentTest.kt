package com.odisby.robolectricpoc.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.common.truth.Truth.assertThat
import com.odisby.robolectricpoc.R
import com.odisby.robolectricpoc.testutils.BaseRobot
import com.odisby.robolectricpoc.testutils.GIVEN
import com.odisby.robolectricpoc.testutils.RUN_INTEGRATED_TEST
import com.odisby.robolectricpoc.testutils.RUN_UNIT_TEST
import com.odisby.robolectricpoc.testutils.THEN
import com.odisby.robolectricpoc.testutils.WHEN
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example integrated tests, which will execute on Robolectric JVM
 *
 * In this example we don't use Context, but you can get it.
 */
@RunWith(RobolectricTestRunner::class)
class FirstFragmentTest {

    private val robot = Robot()

    @Before
    fun setup() {
        robot.setup()
    }

    @Test
    fun shouldGoToNextScreen() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { loginSubmit() }
            THEN { checkViewDisplaySuccessfulLogin() }
        }
    }

//    @Test
//    fun launchFragmentAndVerifyUI() {
//        // use launchInContainer to launch the fragment with UI
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        scenario.moveToState(Lifecycle.State.RESUMED)
//
//        // now use espresso to look for the fragment's text view and verify it is displayed
//        onView(withId(R.id.tv_1)).check(matches(withText("Ruliam")));
//    }

    @Test
    fun `When user clicks on button 1 should change tv text`() {
        /***
         * Code before implementing Robo Pattern
         */
//        // Given
//        val afterClickText = "Botao clicado!"
//
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        onView(withId(R.id.bt_1)).perform(click())
//
//        // Then
//        onView(withId(R.id.tv_1)).check(matches(withText(afterClickText)))


        /**
         * Code with Robo Pattern and some Utils classes
         */
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton1() }
            THEN { checkTV1Text("Botao clicado!") }
        }
    }

    @Test
    fun `When user clicks on button 1 should change tv text 2`() {
        /***
         * Code before implementing Robo Pattern
         */
//        // Given
//        val afterClickText = "Botao clicado!"
//
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        onView(withId(R.id.bt_1)).perform(click())
//
//        // Then
//        onView(withId(R.id.tv_1)).check(matches(withText(afterClickText)))


        /**
         * Code with Robo Pattern and some Utils classes
         */
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton1() }
            THEN { checkTV1Text("Botao clicado!") }
        }
    }

//    @Test
//    fun `When user clicks on button 2 tv visibility should be gone`() {
//        // Given
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        onView(withId(R.id.bt_2)).perform(click())
//
//        // Then
//        onView(withId(R.id.tv_1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
//    }
//
//    @Test
//    fun `When user clicks on button 2 and recreated after tv visibility should be visible`() {
//        // Given
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        onView(withId(R.id.bt_2)).perform(click())
//
//        // The state is saved on fragment, so after recreate it will loose the data
//        scenario.recreate()
//
//        // Then
//        onView(withId(R.id.tv_1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//    }
//
//    @Test
//    fun `When screen starts tv_popup should visibility be gone`() {
//        // Given
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        scenario.moveToState(Lifecycle.State.STARTED)
//
//        // Then
//        onView(withId(R.id.tv_popup)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
//    }
//
//    @Test
//    fun `When click on button 3 tv_popup should be visible`() {
//        // Given
//        val scenario = launchFragmentInContainer<FirstFragment>()
//
//        // When
//        onView(withId(R.id.bt_3)).perform(click())
//
//        // Should be visible even after recreate, because the state is saved on viewmodel
//        scenario.recreate()
//
//        // Then
//        onView(withId(R.id.tv_popup)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//    }
//
//    @Test
//    fun `Testing navigation from FirstFragment to SecondFragment`() {
//        // Given
//        val navController = TestNavHostController(
//            ApplicationProvider.getApplicationContext()
//        )
//        val scenario = launchFragmentInContainer<FirstFragment>()
//        scenario.onFragment { fragment ->
//            navController.setGraph(R.navigation.main_graph)
//
//            Navigation.setViewNavController(fragment.requireView(), navController)
//        }
//
//
//        // When
//        onView(withId(R.id.bt_next_screen)).perform(click())
//
//
//        // Then
//        assertThat(navController.currentDestination?.id).isEqualTo(R.id.secondFragment)
//    }



    private class Robot: BaseRobot() {
//        @RelaxedMockK
//        private lateinit var view: LoginPresenter.View
//        @RelaxedMockK
//        private lateinit var validator: LoginValidator
//
//        private lateinit var tested: LoginPresenter

        var scenario: FragmentScenario<FirstFragment>? = null

        fun createFragment() {
            scenario = launchFragmentInContainer<FirstFragment>()
        }

        fun clickButton1() {
            onView(withId(R.id.bt_1)).perform(click())
        }

        fun checkTV1Text(afterClickText: String) = onView(withId(R.id.tv_1)).check(matches(withText(afterClickText)))

        fun stubValidEmail() {
//            every { validator.isEmailValid(any()) }.returns(true)
        }

        fun stubValidPassword() {
//            every { validator.isPasswordValid(any()) }.returns(true)
        }

        fun loginSubmit() {
//            tested.onLoginSubmit(DataFactory.EMAIL, DataFactory.VALID_PASSWORD)
        }

        fun checkViewClearErrors() {
//            verify { view.clearErrors() }
        }

        fun checkViewDisplaySuccessfulLogin() {
//            verify { view.displaySuccessfulLogin() }
        }
    }
}
