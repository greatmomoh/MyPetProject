package com.example.mypetproject.data.repositories.remote

import com.example.mypetproject.data.core.BaseMapper
import com.example.mypetproject.data.repositories.cache.models.LessonEntity
import com.example.mypetproject.model.LessonModel
import javax.inject.Inject

class LessonMappers @Inject constructor() : BaseMapper<LessonModel, LessonEntity, LessonResponse> {

    override fun mapModelToEntity(model: LessonModel): LessonEntity {
        return with(model) {
            LessonEntity(chapter_id, icon, id, media_url, name, subject_id)
        }
    }

    override fun mapModelToRemote(model: LessonModel): LessonResponse {
        return with(model) {
            LessonResponse(chapter_id, icon, id, media_url, name, subject_id)
        }
    }

    override fun mapEntityToModel(entity: LessonEntity): LessonModel {
        return with(entity) {
            LessonModel(chapter_id, icon, id, media_url, name, subject_id)
        }
    }

    override fun mapEntityToRemote(entity: LessonEntity): LessonResponse {
        return with(entity) {
            LessonResponse(chapter_id, icon, id, media_url, name, subject_id)
        }
    }

    override fun mapRemoteToModel(remote: LessonResponse): LessonModel {
        return with(remote) {
            LessonModel(chapter_id, icon, id, media_url, name, subject_id)
        }
    }

    override fun mapRemoteToEntity(remote: LessonResponse): LessonEntity {
        return with(remote) {
            LessonEntity(chapter_id, icon, id, media_url, name, subject_id)
        }
    }
}