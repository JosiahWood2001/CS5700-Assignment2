package org.example.project

class Shipment(shipmentId: String) : Subject {
    var status: String = ""
        private set
    private val id: String = shipmentId
    var notes: MutableList<String> = mutableListOf()
        private set
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf()
        private set
    var expectedDeliveryDateTimestamp: Long? = null
        private set
    var currentLocation: String = ""
        private set

    private val observers: MutableSet<Observer> = mutableSetOf()

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)

    }
    override fun notifyObserver() {
        for (observer in observers){
            observer.update()
        }
    }
    fun setStatus(newStatus: String){status=newStatus}
    fun setNotes(newNotes: MutableList<String>){notes=newNotes}
    fun setUpdateHistory(newUpdateHistory: MutableList<ShippingUpdate>){updateHistory=newUpdateHistory}
    fun setExpectedDeliveryDateTimestamp(newExpectedDeliveryDateTimestamp: Long){expectedDeliveryDateTimestamp=newExpectedDeliveryDateTimestamp}
    fun setCurrentLocation(newCurrentLocation: String){currentLocation=newCurrentLocation}


    fun addNote(note: String){notes.add(note)}
    fun addUpdate(update: ShippingUpdate){updateHistory.add(update)}
}