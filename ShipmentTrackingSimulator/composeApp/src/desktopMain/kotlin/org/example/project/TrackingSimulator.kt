package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

val handlers: Map<String, UpdateHandler> = mapOf(
    created.type to created, delayed.type to delayed, shipped.type to shipped, lost.type to lost,
    location.type to location, canceled.type to canceled, delivered.type to delivered, noteadded.type to noteadded
)

class TrackingSimulator(private val filename: String) {
    private var shipments = mutableListOf<Shipment>()
    fun findShipment(id:String):Shipment?{
        return shipments.find{it.id == id}
    }
    fun addShipment(shipment:Shipment){
        shipments.add(shipment)
    }
    suspend fun runSimulation(){
        File(filename).useLines {lines ->
            for (line in lines){
                delay(1000)
                val lineElements = line.split(",")
                handlers[lineElements[0]]?.handleUpdate(lineElements)
            }
        }
    }
}