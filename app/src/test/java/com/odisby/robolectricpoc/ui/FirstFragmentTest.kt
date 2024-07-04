package com.odisby.robolectricpoc.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.odisby.robolectricpoc.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FirstFragmentTest {
    @Test
    fun launchFragmentAndVerifyUI() {
        // use launchInContainer to launch the fragment with UI
        val scenario = launchFragmentInContainer<FirstFragment>()

        scenario.moveToState(Lifecycle.State.RESUMED)

        // now use espresso to look for the fragment's text view and verify it is displayed
        onView(withId(R.id.tv_1)).check(matches(withText("Ruliam")));
    }

    @Test
    fun `When user clicks on button 1 should change tv text`() {
        // Given
        val afterClickText = "Botao clicado!"

        val scenario = launchFragmentInContainer<FirstFragment>()

        // When
        onView(withId(R.id.bt_1)).perform(click())

        // Then
        onView(withId(R.id.tv_1)).check(matches(withText(afterClickText)))
    }

    @Test
    fun `When user clicks on button 2 tv visibility should be gone`() {
        // Given
        val scenario = launchFragmentInContainer<FirstFragment>()

        // When
        onView(withId(R.id.bt_2)).perform(click())

        // Then
        onView(withId(R.id.tv_1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `When user clicks on button 2 and recreated after tv visibility should be visible`() {
        // Given
        val scenario = launchFragmentInContainer<FirstFragment>()

        // When
        onView(withId(R.id.bt_2)).perform(click())

        // The state is saved on fragment, so after recreate it will loose the data
        scenario.recreate()

        // Then
        onView(withId(R.id.tv_1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun `When screen starts tv_popup should visibility be gone`() {
        // Given
        val scenario = launchFragmentInContainer<FirstFragment>()

        // When
        scenario.moveToState(Lifecycle.State.STARTED)

        // Then
        onView(withId(R.id.tv_popup)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun `When click on button 3 tv_popup should be visible`() {
        // Given
        val scenario = launchFragmentInContainer<FirstFragment>()

        // When
        onView(withId(R.id.bt_3)).perform(click())

        // Should be visible even after recreate, because the state is saved on viewmodel
        scenario.recreate()

        // Then
        onView(withId(R.id.tv_popup)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}