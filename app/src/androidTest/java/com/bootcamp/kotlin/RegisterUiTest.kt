package com.bootcamp.kotlin

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.ui.register.RegisterFragment
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by jhon on 11/07/2020
 */

@RunWith(AndroidJUnit4::class)
class RegisterUiTest {

    @Rule @JvmField
    var registerRule: ActivityTestRule<MainActivity>
        = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initFragment() {
        registerRule.activity.supportFragmentManager.beginTransaction()
        val scenario = launchFragmentInContainer<RegisterFragment>()
        scenario.recreate()
    }

    @Test
    @Throws(Exception::class)
    fun clickRegisterButton_Empty_Field() {
        val userName = "Jhon"

        onView(withId(R.id.saveUserButton))

        onView(withId(R.id.saveUserButton))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nameUserEditText))
            .perform(typeText(userName), closeSoftKeyboard())

        onView(withId(R.id.nameUserEditText))
            .check(matches(withText("Jhon")))

        onView(allOf(withId(R.id.saveUserButton), not(withText(""))))
            .perform(click())
    }
}