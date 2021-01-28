package com.example.mypetproject.data.repositories.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    val chapter_id: Int,
    val icon: String,
    @PrimaryKey
    val id: Int,
    val media_url: String,
    val name: String,
    val subject_id: Int
)