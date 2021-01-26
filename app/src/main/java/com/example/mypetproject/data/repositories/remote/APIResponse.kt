package com.example.mypetproject.data.repositories.remote

data class APIResponse(
    val `data`: Data
)

data class Data(
    val message: String,
    val status: String,
    val subjects: List<SubjectResponse>
)

data class SubjectResponse(
    val chapters: List<ChapterResponse>,
    val icon: String,
    val id: Int,
    val name: String
)

data class ChapterResponse(
    val id: Int,
    val lessons: List<LessonResponse>,
    val name: String
)

data class LessonResponse(
    val chapter_id: Int,
    val icon: String,
    val id: Int,
    val media_url: String,
    val name: String,
    val subject_id: Int
)