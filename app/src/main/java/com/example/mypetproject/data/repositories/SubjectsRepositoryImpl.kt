package com.example.mypetproject.data.repositories

import com.example.mypetproject.data.core.networkBoundResource
import com.example.mypetproject.data.repositories.cache.SubjectsLocalRepository
import com.example.mypetproject.data.repositories.remote.SubjectsWebService
import com.example.mypetproject.model.SubjectModel
import com.example.mypetproject.utils.PostExecutionThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubjectsRepositoryImpl @Inject constructor(
    private val subjectsWebService: SubjectsWebService,
    private val dispatchers: PostExecutionThread,
    private val mapper: SubjectMappers,
    private val subjectsLocalRepository: SubjectsLocalRepository
) : SubjectsRepository {

    override fun streamUserSubjects(refresh: Boolean): Flow<List<SubjectModel>> {
        return networkBoundResource(query = {
            subjectsLocalRepository.getSubjects().map {
                it.map {
                    mapper.mapEntityToModel(it)
                }
            }
        }, fetch = {
            val response = subjectsWebService.fetchSubjects()
            val mappedList = mapper.mapRemoteToModelList(response.data.subjects)
            mappedList

        }, saveFetchResult = {
            subjectsLocalRepository.clearSubjects()
            it.forEach {
                subjectsLocalRepository.saveSubjects(mapper.mapModelToEntity(it))
            }
        }, shouldFetch = {
            refresh
        }, onFetchFailed = {
            throw it
        }).flowOn(dispatchers.io)
    }

}