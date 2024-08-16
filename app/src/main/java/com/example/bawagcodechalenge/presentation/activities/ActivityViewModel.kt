package com.example.bawagcodechalenge.presentation.activities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bawagcodechalenge.common.Resource
import com.example.bawagcodechalenge.domain.model.use_cases.get_activity.GetActivityUseCase
import com.example.bawagcodechalenge.domain.model.use_cases.get_filtered_activity.GetFilteredActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val getFilteredActivityUseCase: GetFilteredActivityUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state

    init {
        getActivity()
    }

    fun setFilter(filter: String) {
        _state.value = _state.value.copy(filter = filter);
    }

    fun getActivity() {
        if (state.value.filter != null) {
            getFilteredActivity(state.value.filter!!)
        } else {
            getRandomActivity()
        }
    }

    private fun getRandomActivity() {
        getActivityUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = _state.value.copy(activity = it, isLoading = false)
                    } ?: run {
                        _state.value =
                            _state.value.copy(error = "No activity data found", isLoading = false)
                    }
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "Unknown error",
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = ActivityState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFilteredActivity(type: String) {
        getFilteredActivityUseCase(type).onEach { result ->
            run {
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            _state.value.copy(activity = result.data?.get(0), isLoading = false)
                    }

                    is Resource.Error -> {
                        _state.value =
                            _state.value.copy(error = result.message.toString(), isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}