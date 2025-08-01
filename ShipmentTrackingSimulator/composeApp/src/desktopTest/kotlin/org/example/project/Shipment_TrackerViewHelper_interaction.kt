package org.example.project

import kotlin.test.*
import java.text.SimpleDateFormat
import java.util.*

class Shipment_TrackerViewHelper_interaction {
    @BeforeTest
    fun setup() {
        val privateShipments = TrackingSimulator.javaClass.getDeclaredField("shipments")
        privateShipments.isAccessible = true
        val shipments = privateShipments.get(TrackingSimulator)
        when (shipments) {
            is MutableList<*> -> shipments.clear()
            is MutableSet<*> -> shipments.clear()
            else -> error("Unsupported type: ${shipments?.javaClass}")
        }
    }

    @Test
    fun testRegisterAndReceiveShipmentStatusUpdate() {
        val shipment = Shipment("ABC123")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("ABC123")

        shipment.setStatus("shipped")

        assertEquals("shipped", tracker.shipmentStatus)
    }

    @Test
    fun testLocationUpdateIsReflected() {
        val shipment = Shipment("XYZ789")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("XYZ789")

        shipment.setCurrentLocation("Denver, CO")

        assertEquals("Denver, CO", tracker.shipmentLocation)
    }

    @Test
    fun testExpectedDeliveryDateUpdateIsFormattedCorrectly() {
        val shipment = Shipment("DEF456")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("DEF456")

        val timestamp = 1735689600000L // 01/01/2025 12:00:00 AM GMT
        shipment.setExpectedDeliveryDateTimestamp(timestamp)

        val format = SimpleDateFormat("hh:mm:ss MM/dd/yyyy", Locale.getDefault())
        val expected = format.format(Date(timestamp))

        assertEquals(expected, tracker.expectedShipmentDeliveryDate)
    }

    @Test
    fun testAddNoteIsReflectedInTracker() {
        val shipment = Shipment("NOTE001")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("NOTE001")

        shipment.addNote("Fragile - handle with care")

        assertEquals(1, tracker.shipmentNotes.size)
        assertEquals("Fragile - handle with care", tracker.shipmentNotes[0])
    }

    @Test
    fun testUpdateHistoryIsReflected() {
        val shipment = Shipment("UPD001")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("UPD001")

        val update = ShippingUpdate(
            previousStatus = "created",
            newStatus = "shipped",
            timeStamp = 1735689600000L,
            reportMethod = Shipped
        )
        shipment.addUpdate(update)

        assertTrue(tracker.shipmentUpdateHistory.any {
            it.contains("Shipment was shipped")
        })
    }

    @Test
    fun testStopTrackingRemovesObserver() {
        val shipment = Shipment("REMOVE001")
        TrackingSimulator.addShipment(shipment)

        val tracker = TrackerViewHelper()
        tracker.trackShipment("REMOVE001")
        tracker.stopTracking()

        // Change shipment — tracker should not be updated
        shipment.setStatus("delivered")

        // Tracker should still show the old state
        assertNotEquals("delivered", tracker.shipmentStatus)
    }
}