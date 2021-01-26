package com.example.mypetproject.data.core

import androidx.room.TypeConverter
import com.example.mypetproject.data.repositories.cache.models.ChapterEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromChapterList(value: List<ChapterEntity>?): String? {

            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<ChapterEntity>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toChapterList(value: String?): List<ChapterEntity>? {
            if (value == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<ChapterEntity>>() {}.type
            return gson.fromJson(value, type)
        }

    }
}
