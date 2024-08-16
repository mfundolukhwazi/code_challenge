package com.example.bawagcodechalenge.presentation.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.use_cases.get_activity.GetActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase
): ViewModel() {
    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state

    init {
        getActivity()
    }


    private fun getActivity()
    {
        getActivityUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = ActivityState(activity = it)
                    } ?: run {
                        _state.value = ActivityState(error = "No activity data found")
                    }
                }
                is Resource.Error -> {
                    _state.value = ActivityState(error = result.message ?: "Unknown error")
                }
                is Resource.Loading -> {
                    _state.value = ActivityState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}