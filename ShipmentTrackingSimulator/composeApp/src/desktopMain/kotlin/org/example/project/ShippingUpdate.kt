package org.example.project

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ShippingUpdate(
    private var reportMethod: UpdateHandler,
    private var previousStatus:String,
    private var newStatus:String,
    private var timeStamp:Long,
    private var other:String="",
){
    fun getPreviousStatus():String{return previousStatus}
    fun getNewStatus():String{return newStatus}
    fun getTimeStamp():Long{return timeStamp}
    fun setPreviousStatus(status:String){previousStatus=status}
    fun setNewStatus(status:String){newStatus=status}
    fun setTimeStamp(newTimeStamp:Long){timeStamp=newTimeStamp}

    fun getOther():String{return other}
    fun setOther(newOther:String){other=newOther}
    fun getString():String?{
        return reportMethod.reportUpdate(this)
    }
}