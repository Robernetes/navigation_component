package com.vellamars.navcomponent

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicine")
data class Medicine(
    @PrimaryKey var UUID: String,
    @ColumnInfo(name = "medicineItem") var medicineItem: String
    //@ColumnInfo(name = "medicineDate") var medicineDate: String
)