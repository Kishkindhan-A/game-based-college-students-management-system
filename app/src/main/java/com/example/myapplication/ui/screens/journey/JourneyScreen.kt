package com.example.myapplication.ui.screens.journey

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.MockData
import com.example.myapplication.data.models.Mission
import com.example.myapplication.ui.theme.GameBlue
import com.example.myapplication.ui.theme.GameGold
import com.example.myapplication.ui.theme.GamePurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JourneyScreen(currentDay: Int) {
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    if (selectedDay != null) {
        DayMissionOverlay(
            day = selectedDay!!,
            onDismiss = { selectedDay = null }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("WORLD MAP", style = MaterialTheme.typography.titleLarge.copy(letterSpacing = 4.sp, fontWeight = FontWeight.Black)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                JourneyHeader()
            }
            
            val weeks = listOf(
                "REGION 1: SURVIVAL" to 1..7,
                "REGION 2: CONNECTION" to 8..14,
                "REGION 3: GROWTH" to 15..21,
                "REGION 4: FUTURE" to 22..30
            )
            
            weeks.forEach { (title, range) ->
                item {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge.copy(color = GameBlue, letterSpacing = 2.sp),
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }
                
                item {
                    WeekGrid(range, currentDay, onDayClick = { day -> selectedDay = day })
                }
            }
            
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Composable
fun JourneyHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = GamePurple.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Star, contentDescription = null, tint = GameGold, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "30-DAY QUEST",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Unlock all regions to become a College Pro",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.6f))
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeekGrid(range: IntRange, currentDay: Int, onDayClick: (Int) -> Unit) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        range.forEach { day ->
            LevelNode(
                day = day, 
                status = when {
                    day < currentDay -> DayStatus.COMPLETED
                    day == currentDay -> DayStatus.CURRENT
                    else -> DayStatus.LOCKED
                },
                onClick = { onDayClick(day) }
            )
        }
    }
}

enum class DayStatus { COMPLETED, CURRENT, LOCKED }

@Composable
fun LevelNode(day: Int, status: DayStatus, onClick: () -> Unit) {
    val borderColor = when (status) {
        DayStatus.COMPLETED -> Color(0xFF00E676)
        DayStatus.CURRENT -> GameGold
        DayStatus.LOCKED -> Color.White.copy(alpha = 0.1f)
    }
    
    val backgroundColor = when (status) {
        DayStatus.COMPLETED -> Color(0xFF00E676).copy(alpha = 0.1f)
        DayStatus.CURRENT -> GameGold.copy(alpha = 0.2f)
        DayStatus.LOCKED -> Color.Black.copy(alpha = 0.3f)
    }

    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(2.dp, borderColor, CircleShape)
            .clickable(enabled = status != DayStatus.LOCKED, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$day",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    color = if (status == DayStatus.LOCKED) Color.Gray else Color.White
                )
            )
            when (status) {
                DayStatus.COMPLETED -> Icon(Icons.Default.Check, null, modifier = Modifier.size(12.dp), tint = Color(0xFF00E676))
                DayStatus.LOCKED -> Icon(Icons.Default.Lock, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                DayStatus.CURRENT -> Text("NOW", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, color = GameGold))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayMissionOverlay(day: Int, onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "DAY $day MISSIONS",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black, letterSpacing = 2.sp)
            )
            
            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
            
            // Mocking missions for this specific day
            val dayMissions = listOf(
                Mission("${day}1", "Explore Sector $day", "Discover new points of interest", 50, week = 1, day = day),
                Mission("${day}2", "Data Sync", "Check in at the nearest hub", 50, week = 1, day = day)
            )
            
            dayMissions.forEach { mission ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Adjust, null, tint = GameBlue)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = mission.title, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                        Text(text = "REWARD: +${mission.xpReward} XP", style = MaterialTheme.typography.labelSmall.copy(color = GameGold))
                    }
                }
            }
            
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GameBlue)
            ) {
                Text("CLOSE LOG")
            }
        }
    }
}
