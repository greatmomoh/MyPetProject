package com.example.mypetproject.data.repositories

import com.example.mypetproject.data.core.BaseMapper
import com.example.mypetproject.data.repositories.cache.models.SubjectEntity
import com.example.mypetproject.data.repositories.remote.ChapterResponse
import com.example.mypetproject.data.repositories.remote.SubjectResponse
import com.example.mypetproject.model.SubjectModel
import javax.inject.Inject

class SubjectMappers @Inject constructor(private val chapterModelMappers: ChapterModelMappers) :
    BaseMapper<SubjectModel, SubjectEntity, SubjectResponse> {
    override fun mapModelToEntity(model: SubjectModel): SubjectEntity {
        return with(model){
            SubjectEntity(chapterModelMappers.mapModelToEntityList(chapters),icon, id, name)
        }
    }

    override fun mapModelToRemote(model: SubjectModel): SubjectResponse {
        return with(model){
            SubjectResponse(chapterModelMappers.mapModelToRemoteList(chapters),icon, id, name)
        }
    }

    override fun mapEntityToModel(entity: SubjectEntity): SubjectModel {
        return with(entity){
            SubjectModel(chapterModelMappers.mapEntityToModelList(chapters),icon, id, name)
        }
    }

    override fun mapEntityToRemote(entity: SubjectEntity): SubjectResponse {
        return with(entity){
            SubjectResponse(chapterModelMappers.mapEntityToRemoteList(chapters),icon, id, name)
        }
    }

    override fun mapRemoteToModel(remote: SubjectResponse): SubjectModel {
        return with(remote){
            SubjectModel(chapterModelMappers.mapRemoteToModelList(chapters),icon, id, name)
        }
    }

    override fun mapRemoteToEntity(remote: SubjectResponse): SubjectEntity {
        return with(remote){
            SubjectEntity(chapterModelMappers.mapRemoteToEntityList(chapters),icon, id, name)
        }
    }

}