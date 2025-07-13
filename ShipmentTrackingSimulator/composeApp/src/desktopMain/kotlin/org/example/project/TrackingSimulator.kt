package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

val handlers: Map<String, UpdateHandler> = mapOf(
    Created.type to Created, Delayed.type to Delayed, Shipped.type to Shipped, Lost.type to Lost,
    Location.type to Location, Canceled.type to Canceled, Delivered.type to Delivered, Noteadded.type to Noteadded
)

object TrackingSimulator {
    private var shipments = mutableSetOf<Shipment>()
    fun findShipment(id:String):Shipment?{
        return shipments.find{it.getId() == id}
    }
    fun addShipment(shipment:Shipment){
        shipments.add(shipment)
    }
    suspend fun runSimulation(){
        File("test.txt").useLines {lines ->
            for (line in lines){
                delay(1000)
                val lineElements = line.split(",")
                handlers[lineElements[0]]?.handleUpdate(lineElements)
            }
        }
    }
}