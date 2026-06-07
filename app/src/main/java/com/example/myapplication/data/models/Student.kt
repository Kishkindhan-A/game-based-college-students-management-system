package com.example.myapplication.data.models

data class Student(
    val id: String,
    val name: String,
    val email: String,
    val department: String,
    val section: String,
    val profilePhotoUrl: String? = null,
    val level: Int = 1,
    val xp: Int = 0,
    val currentDay: Int = 1,
    val completedMissions: List<String> = emptyList(),
    val earnedBadges: List<String> = emptyList(),
    val interests: List<String> = emptyList()
)

enum class Level(val title: String, val minXp: Int) {
    EXPLORER("Explorer", 0),
    SURVIVOR("Survivor", 500),
    CONNECTOR("Connector", 1500),
    ACHIEVER("Achiever", 3000),
    COLLEGE_PRO("College Pro", 5000);

    companion object {
        fun fromXp(xp: Int): Level {
            return values().findLast { xp >= it.minXp } ?: EXPLORER
        }
    }
}
