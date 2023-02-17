package com.jime.listdetail.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jime.listdetail.data.local.model.OompaLoompaEntity


@Dao
interface OompaLoompaDao {

    @Insert
    suspend fun addOompaLoompa(oompaLoompaEntity: OompaLoompaEntity)

    @Query("SELECT * FROM oompaLoompa WHERE id=:id")
    suspend fun getOompaLoompaById(id: Int): OompaLoompaEntity

    @Delete
    suspend fun deleteOompaLoompa(oompaLoompaEntity: OompaLoompaEntity)

}