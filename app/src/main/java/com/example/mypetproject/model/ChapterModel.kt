package com.example.mypetproject.model

data class ChapterModel(
    val id: Int,
    val lessonModels: List<LessonModel>,
    val name: String
)