package com.example.lpksmartcenter.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


data class SensorData(
    val temperature: String = "0°C",
    val humidity: String = "0%"
)

class SensorsDataBase {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("czujniki")

    fun readData(onDataChanged: (SensorData) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp = snapshot.child("DHT11-temperatura").value
                val humidity = snapshot.child("DHT11-wilgotnosc").value

                onDataChanged(
                    SensorData(
                        temperature = "${temp ?: 0}°C",
                        humidity = "${humidity ?: 0}%"
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                // Obsłuż błąd
            }
        })
    }
}