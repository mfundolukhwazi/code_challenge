package com.example.bawagcodechalenge.presentation.activities.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.example.bawagcodechalenge.common.Constants
import com.example.bawagcodechalenge.presentation.activities.ActivityState
import com.example.bawagcodechalenge.presentation.activities.ActivityViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel
) {
    val state = viewModel.state.value;

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Code Challenge") })
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                } else if (state.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 20.dp)

                    ) {
                        //            Text(text = state.activity)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Type")
                            state.activity?.let { Text(text = it.type) }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Participants")
                            state.activity?.let { Text(text = it.participants.toString()) }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Price")
                            state.activity?.let { Text(text = it.price.toString()) }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Accessibility")
                            state.activity?.let { Text(text = it.accessibility) }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Duration")
                            state.activity?.let { Text(text = it.duration) }
                        }
                        Spacer(modifier = Modifier.fillMaxWidth())
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            onClick = { viewModel.getActivity() }) {
                            Text(text = "Get another activity")
                        }
                        Spacer(modifier = Modifier.fillMaxWidth())
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.EDUCATION.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.EDUCATION)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.EDUCATION)
                                }
                            }
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.RECREATIONAL.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.RECREATIONAL)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.RECREATIONAL)
                                }

                            }
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.SOCIAL.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.SOCIAL)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.SOCIAL)
                                }
                            }
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.CHARITY.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.CHARITY)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.CHARITY)
                                }
                            }
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.COOKING.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.COOKING)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.COOKING)
                                }
                            }
                            Button(onClick = {
                                viewModel.setFilter(
                                    Constants.RELAXATION.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            }) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    showFilterIcon(state, Constants.RELAXATION)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.RELAXATION)
                                }
                            }
                            Button(
                                onClick = {
                                    viewModel.setFilter(
                                        Constants.BUSYWORK.lowercase(
                                            Locale.getDefault()
                                        )
                                    )
                                }) {
                                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                                    showFilterIcon(state, Constants.BUSYWORK)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = Constants.BUSYWORK)
                                }
                            }
                        }

                    }
                }
            }
        }
    )
}

@Composable
fun showFilterIcon(activityState: ActivityState, filter: String) {
    if (activityState.filter == filter.lowercase(Locale.getDefault())) {
        return Icon(imageVector = Icons.Default.Done, contentDescription = null)
    }
}
