package dev.kmturtle.foodium.utils

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.util.Log


fun logSystemInformation(context: Context){
    val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    val batteryStatus: Intent? = context.registerReceiver(null, intentFilter)
    var logMessage = "                                                                             "
    logMessage += "\n============================Build Information============================\n"
    logMessage += "BOOTLOADER: " + Build.BOOTLOADER
    logMessage += "\nFINGERPRINT: " + Build.FINGERPRINT
    logMessage += "\nHARDWARE: " + Build.HARDWARE
    logMessage += "\nID: " + Build.ID
    logMessage += "\nTAGS: " + Build.TAGS
    logMessage += "\nTIME: " + Build.TIME
    logMessage += "\nTYPE: " + Build.TYPE
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        logMessage += "\nODM_SKU: " + Build.ODM_SKU
        logMessage += "\nSKU: " + Build.SKU
    }
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
        logMessage += "\n============================Partition Information============================"
        for(partition in Build.getFingerprintedPartitions()){
            logMessage += "\n----------------------------------------------------------------------"
            logMessage += "\nNAME_PARTITIONS: " + partition.name
            logMessage += "\nFINGERPRINTED_PARTITIONS: " + partition.fingerprint
            logMessage += "\nBUILD_TIME_PARTITIONS: " + partition.buildTimeMillis
        }
        logMessage += "\n----------------------------------------------------------------------"
    }
    logMessage += "\n============================Battery Information============================"
    logMessage += "\nEXTRA_VOLTAGE: " + batteryVoltage(batteryStatus)
    logMessage += "\nEXTRA_TEMPERATURE: " + batteryTemperature(batteryStatus)
    logMessage += "\nBATTERY_LEVEL: " + batteryLevel(batteryStatus)

    Log.v("LogSystemInformation", logMessage)
}

fun batteryLevel(intent: Intent?): String{
    intent?.let {
        val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        return  (level * 100 / scale.toFloat()).toString()
    }?:run{ return "" }
}

fun batteryVoltage(intent: Intent?): String{
    intent?.let {
        val voltage = it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
        return if(voltage > 1000f) (voltage/1000f).toString() + "V" else voltage.toString() + "V"
    }?:run{
        return ""
    }
}

fun batteryTemperature(intent: Intent?): String{
    intent?.let {
        return (it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)/10).toString() + "ºC"
    }?:run{
        return ""
    }
}