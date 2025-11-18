package com.example.lpksmartcenter.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.getValue


/*data class SensorData(
    val temperature: String = "0°C",
    val humidity: String = "0%",
)*/



class SensorsDataBase {
    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("czujniki")



    /*fun readData(onDataChanged: (SensorData) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp = snapshot.child("DHT11-temperatura").value
                val humidity = snapshot.child("DHT11-wilgotnosc").value

                onDataChanged(
                    SensorData(
                        temperature = "${temp ?: 0}°C",
                        humidity = "${humidity ?: 0}%",
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                // Obsłuż błąd
            }
        })
    }*/


    fun readSensorsData(onDataChanged: (Map<String, String>) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensorsMap = mutableMapOf<String, String>()

                snapshot.children.forEach { child ->
                    child.key?.let { key ->
                        val value = child.value
                        // Filtruj tylko wartości liczbowe (czujniki), pomijając Boolean (urządzenia)
                        if (value is Number || value is String) {
                            sensorsMap[key] = value.toString()
                        }
                    }
                }

                onDataChanged(sensorsMap)
            }

            override fun onCancelled(error: DatabaseError) {
                // Obsłuż błąd
            }
        })
    }


    /*fun readDeviceState(deviceKey: String, onStateChanged: (Boolean) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val state = snapshot.child(deviceKey).value as? Boolean ?: false
                onStateChanged(state)
            }

            override fun onCancelled(error: DatabaseError) {
                // Obsłuż błąd
            }
        })
    }

    // Uniwersalna funkcja do zapisu stanu dowolnego urządzenia
    fun writeDeviceState(deviceKey: String, isOn: Boolean, onComplete: (Boolean) -> Unit = {}) {
        database.child(deviceKey).setValue(isOn)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }*/


}





