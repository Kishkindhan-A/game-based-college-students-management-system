package com.example.myapplication.ui.screens.procedures

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.MockData
import com.example.myapplication.ui.theme.GameBlue
import com.example.myapplication.ui.theme.GameGold

data class Procedure(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val description: String,
    val steps: List<String> = emptyList(),
    val documents: List<String> = emptyList(),
    val deadline: String = "N/A"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceduresScreen() {
    var selectedProcedure by remember { mutableStateOf<Procedure?>(null) }
    val procedures = MockData.drngpitProcedures

    if (selectedProcedure != null) {
        ProcedureDetailOverlay(
            procedure = selectedProcedure!!,
            onDismiss = { selectedProcedure = null }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("KNOWLEDGE BASE", style = MaterialTheme.typography.titleLarge.copy(letterSpacing = 2.sp, fontWeight = FontWeight.Black)) },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, tint = GameGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("DR.NGPIT PROCEDURAL DATABASE", style = MaterialTheme.typography.labelSmall.copy(color = GameGold))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            items(procedures) { procedure ->
                EntryCard(
                    procedure = procedure,
                    onClick = { selectedProcedure = procedure }
                )
            }
        }
    }
}

@Composable
fun EntryCard(procedure: Procedure, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = procedure.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = GameBlue
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = procedure.title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                Text(text = procedure.description, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
            }
            Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureDetailOverlay(procedure: Procedure, onDismiss: () -> Unit) {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(procedure.icon, null, tint = GameBlue, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = procedure.title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            
            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
            
            Text("MISSION OBJECTIVES (STEPS)", style = MaterialTheme.typography.labelLarge.copy(color = GameGold))
            procedure.steps.forEachIndexed { index, step ->
                Row {
                    Text("${index + 1}.", color = GameBlue, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(step, style = MaterialTheme.typography.bodyMedium)
                }
            }
            
            Text("REQUIRED LOOT (DOCUMENTS)", style = MaterialTheme.typography.labelLarge.copy(color = GameGold))
            procedure.documents.forEach { doc ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Description, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(doc, style = MaterialTheme.typography.bodyMedium)
                }
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GameBlue.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("TIME LIMIT:", style = MaterialTheme.typography.labelMedium.copy(color = GameBlue))
                Text(procedure.deadline, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
            }
            
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GameBlue)
            ) {
                Text("UNDERSTOOD")
            }
        }
    }
}
