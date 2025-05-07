package com.salmafahira0038.miniproject2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val deskripsi: String,
    val tahunRilis: String
)