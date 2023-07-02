package com.vellamars.navcomponent

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Medicine::class],
    version = 1
)
abstract class DBMedicine: RoomDatabase() {
    abstract fun daoMedicine(): DaoMedicine
}