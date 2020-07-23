package com.bootcamp.kotlin.ui

import FavoriteFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.bootcamp.kotlin.R
import com.bootcamp.kotlin.data.server.ApiClient
import com.bootcamp.kotlin.ui.main.MainActivity
import com.bootcamp.kotlin.util.fromJson
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

class FavoritesUiTest : KoinTest {
    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        loadData()
    }

    private fun loadData() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("favoritesmovies.json")
        )

        val resource = OkHttp3IdlingResource.create("OkHttp", ApiClient.okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun clickAMovieNavigatesToDetail() {
        activityTestRule.launchActivity(null)
        launchFragmentInContainer<FavoriteFragment>()
        Thread.sleep(500)

        onView(withId(R.id.favoriteMoviesRecyclerView))
            .check(matches(hasDescendant(withText("Star Wars: The Rise of Skywalker"))))

        onView(withId(R.id.favoriteMoviesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
    }
}