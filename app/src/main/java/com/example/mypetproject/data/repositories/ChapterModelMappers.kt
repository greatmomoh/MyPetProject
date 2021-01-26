package com.example.mypetproject.data.repositories

import com.example.mypetproject.data.core.BaseMapper
import com.example.mypetproject.data.repositories.cache.models.ChapterEntity
import com.example.mypetproject.data.repositories.remote.ChapterResponse
import com.example.mypetproject.data.repositories.remote.LessonMappers
import com.example.mypetproject.model.ChapterModel
import javax.inject.Inject

class ChapterModelMappers @Inject constructor(private val lessonMappers: LessonMappers) :
    BaseMapper<ChapterModel, ChapterEntity, ChapterResponse> {
    override fun mapModelToEntity(model: ChapterModel): ChapterEntity {
        return with(model) {
            ChapterEntity(id, lessonMappers.mapModelToEntityList(lessons), name)
        }
    }

    override fun mapModelToRemote(model: ChapterModel): ChapterResponse {
        return with(model){
            ChapterResponse(id, lessonMappers.mapModelToRemoteList(lessons), name)
        }
    }

    override fun mapEntityToModel(entity: ChapterEntity): ChapterModel {
        return with(entity){
            ChapterModel(id, lessonMappers.mapEntityToModelList(lessons), name)
        }
    }

    override fun mapEntityToRemote(entity: ChapterEntity): ChapterResponse {
        return with(entity){
            ChapterResponse(id, lessonMappers.mapEntityToRemoteList(lessons), name)
        }
    }

    override fun mapRemoteToModel(remote: ChapterResponse): ChapterModel {
        return with(remote){
            ChapterModel(id, lessonMappers.mapRemoteToModelList(lessons), name)
        }
    }

    override fun mapRemoteToEntity(remote: ChapterResponse): ChapterEntity {
        return with(remote){
            ChapterEntity(id, lessonMappers.mapRemoteToEntityList(lessons), name)
        }
    }

}