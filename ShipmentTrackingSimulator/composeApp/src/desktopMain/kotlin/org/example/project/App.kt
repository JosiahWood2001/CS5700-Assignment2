package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import shipmenttrackingsimulator.composeapp.generated.resources.Res
import shipmenttrackingsimulator.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val simulator = remember { TrackingSimulator("test.txt")}
    LaunchedEffect(simulator) {
        simulator.runSimulation()
    }
    MaterialTheme {
        Column {
            Divider(Modifier.height(5.dp))
            ShipmentSubscriptions()
        }
    }
}