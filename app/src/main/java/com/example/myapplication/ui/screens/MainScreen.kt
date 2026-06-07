package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.MockData
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.assistant.AssistantScreen
import com.example.myapplication.ui.screens.dashboard.DashboardScreen
import com.example.myapplication.ui.screens.journey.JourneyScreen
import com.example.myapplication.ui.screens.map.MapScreen
import com.example.myapplication.ui.screens.procedures.ProceduresScreen
import com.example.myapplication.ui.screens.timetable.TimetableScreen
import com.example.myapplication.ui.theme.GameBlue
import com.example.myapplication.ui.theme.GameGold
import com.example.myapplication.ui.theme.GamePurple

sealed class BottomNavItem(val screen: Screen, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem(Screen.Dashboard, Icons.Default.Home, "BASE")
    object Journey : BottomNavItem(Screen.Journey, Icons.Default.Map, "QUESTS")
    object Map : BottomNavItem(Screen.Map, Icons.Default.Navigation, "MAP")
    object Timetable : BottomNavItem(Screen.Timetable, Icons.Default.Schedule, "LOGS")
    object Procedures : BottomNavItem(Screen.Procedures, Icons.Default.Info, "GUIDES")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Journey,
        BottomNavItem.Map,
        BottomNavItem.Timetable,
        BottomNavItem.Procedures
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF121212),
                contentColor = GameBlue,
                tonalElevation = 8.dp,
                modifier = Modifier.height(80.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                item.icon, 
                                contentDescription = null,
                                modifier = Modifier.size(if (selected) 28.dp else 24.dp)
                            ) 
                        },
                        label = { 
                            Text(
                                item.label, 
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    letterSpacing = 1.sp
                                )
                            ) 
                        },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = GameGold,
                            selectedTextColor = GameGold,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = GamePurple.copy(alpha = 0.3f)
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.route != Screen.Assistant.route) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Assistant.route) },
                    containerColor = GameGold,
                    contentColor = Color.Black,
                    shape = CircleShape,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(Icons.Default.SmartToy, contentDescription = "AI Assistant")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding).background(
                Brush.verticalGradient(
                    listOf(Color(0xFF121212), Color(0xFF1E1E2E))
                )
            )
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    student = MockData.drngpitStudent,
                    todaysMissions = MockData.drngpitMissions,
                    onMissionClick = { /* Handled in Dashboard */ }
                )
            }
            composable(Screen.Journey.route) { 
                JourneyScreen(currentDay = MockData.drngpitStudent.currentDay)
            }
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Timetable.route) { TimetableScreen() }
            composable(Screen.Procedures.route) { 
                ProceduresScreen() 
            }
            composable(Screen.Assistant.route) {
                AssistantScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
