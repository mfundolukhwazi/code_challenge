package com.example.bawagcodechalenge.domain.model.use_cases.get_activity

import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.Activity
import com.example.bawagcodechalenge.domain.model.use_cases.get_filtered_activity.GetFilteredActivityUseCase
import com.example.bawagcodechalenge.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetActivityUseCaseTest {
    private lateinit var getActivityUseCase: GetActivityUseCase
    private lateinit var getFilteredActivityUseCase: GetFilteredActivityUseCase
    private lateinit var activityRepository: ActivityRepository

    @Before
    fun setUp() {
        activityRepository = mock(ActivityRepository::class.java)
        getActivityUseCase = GetActivityUseCase(activityRepository)
        getFilteredActivityUseCase = GetFilteredActivityUseCase(activityRepository)
    }

    @Test
    fun `invoke should return success when repository returns activity`() = runTest {

        val activity = Activity(
            activity = "Learn Express.js",
            availability = 0.25,
            type = "education",
            participants = 1,
            price = 0.1,
            accessibility = "Few to no challenges",
            duration = "hours",
            kidFriendly = true,
            link = "https://expressjs.com/",
            key = "3943506"
        )
        whenever(activityRepository.getActivity()).thenReturn(activity)

        val result = getActivityUseCase().toList()
        assertTrue(result[1] is Resource.Success<Activity>)
        assertEquals(activity, (result[1] as Resource.Success<Activity>).data)
    }

    @Test
    fun `invoke should return error when repository throws exception`() = runTest {

        val errorMessage = "An error occurred"
        whenever(activityRepository.getActivity()).thenThrow(RuntimeException(errorMessage))

        val result = getActivityUseCase().toList()
        assertTrue(result[1] is Resource.Error<Activity>)
        assertEquals(errorMessage, (result[1] as Resource.Error<Activity>).message)
    }

    @Test
    fun `invoke should emit loading followed by success`() = runTest {
        val activity = Activity(
            activity = "Learn Express.js",
            availability = 0.25,
            type = "education",
            participants = 1,
            price = 0.1,
            accessibility = "Few to no challenges",
            duration = "hours",
            kidFriendly = true,
            link = "https://expressjs.com/",
            key = "3943506"
        )
        whenever(activityRepository.getActivity()).thenReturn(activity)
        val result = getActivityUseCase().toList()

        assertTrue(result[0] is Resource.Loading<Activity>)
        assertTrue(result[1] is Resource.Success<Activity>)
        assertEquals(activity, (result[1] as Resource.Success<Activity>).data)
    }

    @Test
    fun `invoke return a list of activities filtered by type if filter is applied`() = runTest {
        val activities = listOf(
            Activity(
                activity = "Learn Express.js",
                availability = 0.25,
                type = "education",
                participants = 1,
                price = 0.1,
                accessibility = "Few to no challenges",
                duration = "hours",
                kidFriendly = true,
                link = "https://expressjs.com/",
                key = "3943506"
            ),
            Activity(
                activity = "Go for a walk",
                availability = 0.5,
                type = "recreational",
                participants = 1,
                price = 0.0,
                accessibility = "Easily accessible",
                duration = "minutes",
                kidFriendly = true,
                link = "https://example.com/walk",
                key = "9020130"
            ),
            Activity(
                activity = "Read a book",
                availability = 0.3,
                type = "education",
                participants = 1,
                price = 0.0,
                accessibility = "Few to no challenges",
                duration = "hours",
                kidFriendly = true,
                link = "https://example.com/book",
                key = "5843302"
            )
        )
        val filteredType = "education"
        val filteredActivities = activities.filter { it.type == filteredType }

        whenever(activityRepository.filterActivity(filteredType)).thenReturn(filteredActivities)
        val result = activityRepository.filterActivity(filteredType)

        assertEquals(2, result.size)
        assertTrue(result.all { it.type == filteredType })
        assertEquals(filteredActivities, result)
    }
}