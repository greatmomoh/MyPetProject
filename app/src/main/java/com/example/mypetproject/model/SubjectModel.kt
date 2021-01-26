package com.example.mypetproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubjectModel(
    val chapters: List<ChapterModel>,
    val icon: String,
    val id: Int,
    val name: String
) : Parcelable