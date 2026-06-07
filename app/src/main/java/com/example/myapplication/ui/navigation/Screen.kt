package com.example.myapplication.ui.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Journey : Screen("journey")
    object Map : Screen("map")
    object Timetable : Screen("timetable")
    object Procedures : Screen("procedures")
    object Assistant : Screen("assistant")
}
