package org.example.project

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ShipmentSubscriptions(){
    val shipments = remember{ mutableStateListOf<Shipment>()}
    var searchField by remember{ mutableStateOf("")}
    Row{
        TextField(searchField, onValueChange={searchField=it})
        Button(onClick = {
            //shipments += Shipment()
            searchField = ""
        }){
            Text("Search")
        }
    }
    LazyColumn{
        items(shipments){
            Text("this")
        }
    }
}