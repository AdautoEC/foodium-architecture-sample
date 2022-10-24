package dev.kmturtle.foodium.utils

import android.os.BatteryManager
import android.os.Build
import android.util.Log

fun logSystemInformation(){
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
    logMessage += "\nBATTERY_PROPERTY_CAPACITY: " +
            BatteryManager.BATTERY_PROPERTY_CAPACITY
    logMessage += "\nBATTERY_PROPERTY_CURRENT_AVERAGE: " +
            BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE
    logMessage += "\nBATTERY_PROPERTY_CURRENT_NOW: " +
            BatteryManager.BATTERY_PROPERTY_CURRENT_NOW
    logMessage += "\nBATTERY_PROPERTY_ENERGY_COUNTER: " +
            BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER

    Log.v("LogSystemInformation", logMessage)
}