package com.example.bawagcodechalenge.presentation.filter_activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.use_cases.get_filtered_activity.GetFilteredActivityUseCase
import com.example.bawagcodechalenge.presentation.activities.ActivityState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FilterActivityViewModel @Inject constructor(
    private val getFilteredActivity: GetFilteredActivityUseCase
): ViewModel() {

    init {
        getFilterActivity("Education")
    }

    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state

    private fun getFilterActivity(type: String)
    {
        getFilteredActivity(type).onEach {result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _state.value = ActivityState(activity = result.data?.get(0))
                    }

                    is Resource.Error -> {
                        _state.value = ActivityState(error = result.message.toString())
                    }

                    is Resource.Loading -> {
                        _state.value = ActivityState(isLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}