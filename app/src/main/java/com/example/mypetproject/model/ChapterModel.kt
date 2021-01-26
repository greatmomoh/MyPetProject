package com.example.mypetproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChapterModel(
    val id: Int,
    val lessons: List<LessonModel>,
    val name: String
) : Parcelable