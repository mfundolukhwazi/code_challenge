package com.example.bawagcodechalenge.presentation.activities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bawagcodechalenge.presentation.activities.ActivityViewModel

@Composable
fun ActivityScreen (
    viewModel: ActivityViewModel
){
    val state = viewModel.state.value;

    Box (modifier = Modifier.fillMaxWidth().padding(all = 20.dp)){
        Column(
            modifier = Modifier.fillMaxWidth()

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Filter")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Filter")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Filter")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Filter")
                }
            }

        }
    }

    if(state.error.isNotBlank())
    {
        Text(text = state.error,
            color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp))
    }
    if (state.isLoading)
    {
        CircularProgressIndicator()
    }
}