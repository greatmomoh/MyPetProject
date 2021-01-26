package com.example.mypetproject.data.repositories.cache.models

data class ChapterEntity(
    val id: Int,
    val lessons: List<LessonEntity>,
    val name: String
)