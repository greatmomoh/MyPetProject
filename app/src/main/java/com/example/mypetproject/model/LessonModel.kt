package com.example.mypetproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LessonModel(
    val chapter_id: Int,
    val icon: String,
    val id: Int,
    val media_url: String,
    val name: String,
    val subject_id: Int
) : Parcelable