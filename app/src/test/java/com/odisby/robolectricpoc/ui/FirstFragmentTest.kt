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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example integrated tests, which will execute on Robolectric JVM
 *
 * In this example we don't use Context, but you can get it.
 */
@RunWith(RobolectricTestRunner::class)
class FirstFragmentTest {

    private val robot = FirstFragmentRobot()

    @Before
    fun setup() {
        robot.setup()
    }

    @After
    fun tearDown() {
        robot.tearsDown()
    }

    @Test
    fun launchFragmentAndVerifyUI() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { moveToState(Lifecycle.State.RESUMED) }
            THEN { checkTV1Text("Ruliam") }
        }
    }

    @Test
    fun `When user clicks on button 1 should change tv text`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton1() }
            THEN { checkTV1Text("Botao clicado!") }
        }
    }

    @Test
    fun `When user clicks on button 1 should change tv text 2`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton1() }
            THEN { checkTV1Text("Botao clicado!") }
        }
    }

    @Test
    fun `When user clicks on button 2 tv visibility should be gone`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton2() }
            THEN { checkTV1TextVisibility(ViewMatchers.Visibility.GONE) }
        }
    }

    @Test
    fun `When user clicks on button 2 and recreated after tv visibility should be visible`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton2() }
            WHEN { recreateFragment() }
            THEN { checkTV1TextVisibility(ViewMatchers.Visibility.VISIBLE) }
        }
    }

    @Test
    fun `When screen starts tv_popup should visibility be gone`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { moveToState(Lifecycle.State.STARTED) }
            THEN { checkTVPopUpTextVisibility(ViewMatchers.Visibility.GONE) }
        }
    }

    @Test
    fun `When click on button 3 tv_popup should be visible`() {
        RUN_INTEGRATED_TEST(robot) {
            GIVEN { createFragment() }
            WHEN { clickButton3() }
            // Should be visible even after recreate, because the state is saved on viewmodel
            WHEN { recreateFragment() }
            THEN { checkTV1TextVisibility(ViewMatchers.Visibility.VISIBLE) }
        }
    }

    @Test
    fun `Testing navigation from FirstFragment to SecondFragment`() {
        RUN_UNIT_TEST(robot) {
            GIVEN { createFragment() }
            GIVEN { createAndSetNavController() }
            WHEN { clickButtonNextScreen() }
            THEN { assertCurrentDestination(R.id.secondFragment) }
        }
    }


    private class FirstFragmentRobot: BaseRobot() {
//        @RelaxedMockK
//        private lateinit var view: LoginPresenter.View
//        @RelaxedMockK
//        private lateinit var validator: LoginValidator
//
//        private lateinit var tested: LoginPresenter

        private var scenario: FragmentScenario<FirstFragment>? = null
        private var navController: TestNavHostController? = null

        override fun tearsDown() {
            scenario?.close()
        }

        fun createFragment() {
            scenario = launchFragmentInContainer<FirstFragment>()
        }

        fun createAndSetNavController() {
            navController = TestNavHostController(
                ApplicationProvider.getApplicationContext()
            )

            scenario?.onFragment { fragment ->
                navController!!.setGraph(R.navigation.main_graph)

                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }

        /*
         * Click Buttons actions
         */

        fun clickButton1() {
            onView(withId(R.id.bt_1)).perform(click())
        }

        fun clickButton2() {
            onView(withId(R.id.bt_2)).perform(click())
        }

        fun clickButton3() {
            onView(withId(R.id.bt_3)).perform(click())
        }

        fun clickButtonNextScreen() {
            onView(withId(R.id.bt_next_screen)).perform(click())
        }

        /*
         * Check Views text and visibility
         */

        fun checkTV1Text(afterClickText: String) {
            onView(withId(R.id.tv_1)).check(matches(withText(afterClickText)))
        }

        fun checkTV1TextVisibility(visibility: ViewMatchers.Visibility) {
            onView(withId(R.id.tv_1)).check(matches(withEffectiveVisibility(visibility)))
        }

        fun checkTVPopUpTextVisibility(visibility: ViewMatchers.Visibility) {
            onView(withId(R.id.tv_popup)).check(matches(withEffectiveVisibility(visibility)))
        }

        /*
         * Fragment Utils
         */

        fun moveToState(lifecycle: Lifecycle.State) {
            scenario?.moveToState(lifecycle)
        }

        fun recreateFragment() {
            scenario?.recreate()
        }

        /*
         * NavController Utils
         */
        fun assertCurrentDestination(fragmentId: Int) {
            assertThat(navController!!.currentDestination?.id).isEqualTo(fragmentId)
        }
    }
}
