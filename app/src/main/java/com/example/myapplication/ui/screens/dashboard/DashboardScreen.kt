package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.MockData
import com.example.myapplication.data.models.Level
import com.example.myapplication.data.models.Mission
import com.example.myapplication.data.models.Student
import com.example.myapplication.ui.theme.GameGold
import com.example.myapplication.ui.theme.GameNeon
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    student: Student,
    todaysMissions: List<Mission>,
    onMissionClick: (Mission) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "PLAYER: ${student.name.uppercase()}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Black
                        )
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = GameGold)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                CharacterStatsCard(student)
            }

            item {
                BadgesRow()
            }
            
            item {
                Text(
                    text = "ACTIVE QUESTS",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = GameGold,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(todaysMissions) { mission ->
                QuestItem(mission, onMissionClick)
            }

            item {
                Text(
                    text = "POTENTIAL ALLIES",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = GameGold,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(MockData.friends) { friend ->
                        FriendCard(friend)
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO: LOST MODE */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .border(2.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.2f),
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Warning, contentDescription = null)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "EMERGENCY: I AM LOST",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun BadgesRow() {
    Column {
        Text(
            text = "UNLOCKED BADGES",
            style = MaterialTheme.typography.labelLarge.copy(color = GameGold, letterSpacing = 2.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MockData.badges.forEach { badge ->
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, GameGold.copy(alpha = 0.3f))
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.MilitaryTech, 
                            contentDescription = badge.name,
                            tint = GameGold,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FriendCard(friend: com.example.myapplication.data.Friend) {
    Card(
        modifier = Modifier.width(160.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(Icons.Default.AccountCircle, null, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = friend.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Text(text = friend.department, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "XP: ${friend.xp}", style = MaterialTheme.typography.labelSmall, color = GameGold)
        }
    }
}

@Composable
fun CharacterStatsCard(student: Student) {
    val level = Level.fromXp(student.xp)
    val progress = (student.xp % 500) / 500f // Simplified progress logic
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "RANK: ${level.title}",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "TOTAL XP: ${student.xp}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = GameGold)
                    )
                }
                
                Surface(
                    modifier = Modifier.size(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(32.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "LEVEL PROGRESS",
                style = MaterialTheme.typography.labelSmall.copy(color = Color.White.copy(alpha = 0.6f))
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            // Custom Game-style Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .background(
                            Brush.horizontalGradient(
                                listOf(MaterialTheme.colorScheme.primary, GameNeon)
                            )
                        )
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${(progress * 100).toInt()}%", style = MaterialTheme.typography.labelSmall)
                Text(text = "NEXT RANK: 500 XP", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun QuestItem(mission: Mission, onClick: (Mission) -> Unit) {
    Surface(
        onClick = { onClick(mission) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = if (mission.isCompleted) Color.White.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            1.dp, 
            if (mission.isCompleted) GameNeon.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (mission.isCompleted) GameNeon.copy(alpha = 0.2f) 
                        else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (mission.isCompleted) Icons.Default.CheckCircle else Icons.Default.Adjust,
                    contentDescription = null,
                    tint = if (mission.isCompleted) GameNeon else MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mission.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (mission.isCompleted) Color.Gray else Color.White
                    )
                )
                Text(
                    text = "REWARD: +${mission.xpReward} XP",
                    style = MaterialTheme.typography.labelMedium.copy(color = GameGold)
                )
            }
            
            if (!mission.isCompleted) {
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White.copy(alpha = 0.3f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    MyApplicationTheme {
        DashboardScreen(
            student = Student("1", "Karthik", "k@c.edu", "CS", "A", xp = 650),
            todaysMissions = listOf(
                Mission("1", "Find Classroom", "Locate your first classroom", 50, week = 1, day = 1, isCompleted = true),
                Mission("2", "Visit Library", "Go to the central library", 50, week = 1, day = 1),
                Mission("3", "Join Department Group", "Join the official group", 50, week = 1, day = 1)
            ),
            onMissionClick = {}
        )
    }
}
