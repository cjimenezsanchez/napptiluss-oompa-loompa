package com.jime.listdetail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jime.listdetail.data.local.model.OompaLoompaEntity

@Database(
    entities = [OompaLoompaEntity::class],
    version = 1
)
abstract class OompaLoompaDatabase : RoomDatabase() {

    abstract val dao: OompaLoompaDao

}