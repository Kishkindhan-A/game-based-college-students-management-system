package com.example.myapplication.ui.screens.assistant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
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
import com.example.myapplication.ui.theme.GameBlue
import com.example.myapplication.ui.theme.GameGold

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantScreen(onBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        ChatMessage("Greetings, Player! I am your NGP-Droid. Ask me about classrooms, scholarships, or missions.", false)
    ) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.SmartToy, null, tint = GameGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("AI ASSISTANT", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black, letterSpacing = 2.sp))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                // Suggested Prompts
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(MockData.suggestedPrompts) { prompt ->
                        SuggestionChip(
                            onClick = { inputText = prompt },
                            label = { Text(prompt, fontSize = 10.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                labelColor = GameBlue,
                                containerColor = GameBlue.copy(alpha = 0.1f)
                            )
                        )
                    }
                }
                
                Surface(tonalElevation = 3.dp) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Enter Command...") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                        IconButton(onClick = {
                            if (inputText.isNotBlank()) {
                                messages.add(ChatMessage(inputText, true))
                                // Mock response based on input
                                val response = when {
                                    inputText.contains("classroom", true) -> "Scanning... Your classes are primarily in Block A."
                                    inputText.contains("scholarship", true) -> "Accessing DB... Scholarship details are in Block B, Room 102."
                                    else -> "Processing request... My knowledge base is updating."
                                }
                                messages.add(ChatMessage(response, false))
                                inputText = ""
                            }
                        }) {
                            Icon(Icons.Default.Send, contentDescription = "Send", tint = GameBlue)
                        }
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.End else Alignment.Start
    val color = if (message.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (message.isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = alignment) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = if (message.isUser) 12.dp else 0.dp,
                    bottomEnd = if (message.isUser) 0.dp else 12.dp
                ))
                .background(color)
                .padding(12.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(text = message.text, color = textColor, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
