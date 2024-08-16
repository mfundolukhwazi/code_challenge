package com.example.bawagcodechalenge.data.remote.dto

import com.example.bawagcodechalenge.domain.model.Activity

data class ActivityDto(
    val accessibility: String,
    val activity: String,
    val availability: Double,
    val duration: String,
    val key: String,
    val kidFriendly: Boolean,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)

fun ActivityDto.toActivity(): Activity {
    return  Activity(
        accessibility = accessibility,
        activity = activity,
        availability = availability,
        duration = duration,
        key = key,
        kidFriendly = kidFriendly,
        link = link,
        participants = participants,
        price = price,
        type = type
    );
}