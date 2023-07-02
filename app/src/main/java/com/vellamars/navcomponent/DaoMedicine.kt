package com.vellamars.navcomponent

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoMedicine {
    @Query("SELECT * FROM medicine")
    suspend fun getMedicines(): MutableList<Medicine>

    @Insert
    suspend fun addMedicine(medicine: Medicine)

    @Query("Delete from medicine where UUID =:uuid")
    suspend fun delMedicine(uuid: String)
}