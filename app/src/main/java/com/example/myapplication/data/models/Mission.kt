package com.example.myapplication.data.models

data class Mission(
    val id: String,
    val title: String,
    val description: String,
    val xpReward: Int,
    val badgeReward: Badge? = null,
    val isCompleted: Boolean = false,
    val week: Int,
    val day: Int
)

data class Badge(
    val id: String,
    val name: String,
    val icon: String // Resource name or URL
)
