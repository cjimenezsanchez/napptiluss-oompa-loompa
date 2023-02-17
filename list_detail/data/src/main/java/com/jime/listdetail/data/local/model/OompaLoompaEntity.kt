package com.jime.listdetail.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "oompaLoompa")
data class OompaLoompaEntity(
  @PrimaryKey
  val id: Int,
  val name: String,
  val lastName: String,
  val email: String,
  val profession: String,
  val gender: String,
  val image: String
)