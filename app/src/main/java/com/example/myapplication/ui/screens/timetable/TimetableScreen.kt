package com.example.myapplication.ui.screens.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.GameBlue

data class ClassSchedule(
    val subject: String,
    val time: String,
    val location: String,
    val faculty: String,
    val isCurrent: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen() {
    val schedules = listOf(
        ClassSchedule("DATA STRUCTURES", "09:40 - 10:40", "BLOCK A - 202", "DR. PRIYA", true),
        ClassSchedule("DIGITAL ELECTRONICS", "10:40 - 11:40", "BLOCK A - 305", "PROF. RAMAN"),
        ClassSchedule("BREAK", "11:40 - 11:50", "CAMPUS", "-"),
        ClassSchedule("OPERATING SYSTEMS", "11:50 - 12:50", "BLOCK A - 204", "MS. ANITHA"),
        ClassSchedule("RECHARGE (LUNCH)", "12:50 - 13:30", "G MART / CANTEEN", "-"),
        ClassSchedule("JAVA PROGRAMMING", "13:30 - 15:30", "BLOCK A - LAB 2", "MR. SURESH"),
        ClassSchedule("PLACEMENT TRAINING", "15:30 - 16:30", "AUDITORIUM", "CD CELL")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("MISSION LOGS", style = MaterialTheme.typography.titleLarge.copy(letterSpacing = 2.sp, fontWeight = FontWeight.Black)) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "DR.NGPIT // DAY ORDER SYSTEM",
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray, letterSpacing = 1.sp)
                    )
                    Icon(Icons.Default.Wifi, contentDescription = null, tint = GameBlue, modifier = Modifier.size(16.dp))
                }
            }
            
            items(schedules) { schedule ->
                MissionCard(schedule)
            }
        }
    }
}

@Composable
fun MissionCard(schedule: ClassSchedule) {
    val borderColor = if (schedule.isCurrent) GameBlue else Color.White.copy(alpha = 0.1f)
    val bgColor = if (schedule.isCurrent) GameBlue.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, null, modifier = Modifier.size(14.dp), tint = GameBlue)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = schedule.time, 
                        style = MaterialTheme.typography.labelSmall.copy(color = GameBlue)
                    )
                }
                if (schedule.isCurrent) {
                    Text(
                        "● IN PROGRESS", 
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.Red, fontWeight = FontWeight.Bold)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = schedule.subject, 
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocationOn, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = schedule.location, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = schedule.faculty, style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray))
            }
        }
    }
}
