package com.example.mypetproject.model

data class SubjectModel(
    val chapters: List<ChapterModel>,
    val icon: String,
    val id: Int,
    val name: String
)