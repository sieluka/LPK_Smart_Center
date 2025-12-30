package com.example.lpksmartcenter.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SensorsDataBase {
    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("czujniki")

    fun readSensorsData(onDataChanged: (Map<String, Any>) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensorsMap = mutableMapOf<String, Any>()

                snapshot.children.forEach { child ->
                    child.key?.let { key ->
                        child.value?.let { value ->
                            sensorsMap[key] = value
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
}





