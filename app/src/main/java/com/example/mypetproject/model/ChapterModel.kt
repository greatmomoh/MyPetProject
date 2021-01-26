package com.example.mypetproject.model

data class ChapterModel(
    val id: Int,
    val lessons: List<LessonModel>,
    val name: String
)