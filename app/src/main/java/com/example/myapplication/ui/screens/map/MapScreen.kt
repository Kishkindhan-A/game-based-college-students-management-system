package com.example.myapplication.ui.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.data.MockData
import com.example.myapplication.ui.theme.GameBlue
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    val drngpitCenter = GeoPoint(11.0505, 77.0398)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("TACTICAL OVERLAY", style = MaterialTheme.typography.titleLarge.copy(letterSpacing = 2.sp, fontWeight = FontWeight.Black)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AndroidView(
                factory = { context ->
                    MapView(context).apply {
                        setTileSource(TileSourceFactory.MAPNIK)
                        setMultiTouchControls(true)
                        controller.setZoom(18.5)
                        controller.setCenter(drngpitCenter)
                        
                        // Add markers for all campus locations
                        MockData.locations.forEach { location ->
                            val marker = Marker(this)
                            marker.position = location.point
                            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            marker.title = location.name
                            marker.snippet = location.description
                            overlays.add(marker)
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // UI Overlays
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                        .border(1.dp, GameBlue.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = GameBlue)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("LOCATING ${MockData.locations.size} NODES...", color = GameBlue.copy(alpha = 0.5f), fontSize = 14.sp)
                    }
                }
            }

            Button(
                onClick = { /* TODO: LOST MODE */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
                    .height(56.dp)
                    .fillMaxWidth(0.8f)
                    .border(1.dp, Color.Red, RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.2f),
                    contentColor = Color.Red
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Icon(Icons.Default.GpsFixed, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text("INITIATE RESCUE PROTOCOL (LOST)", fontWeight = FontWeight.Bold)
            }
        }
    }
}
