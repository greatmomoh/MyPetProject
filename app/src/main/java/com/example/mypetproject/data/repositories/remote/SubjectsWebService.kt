package com.example.mypetproject.data.repositories.remote

import retrofit2.http.GET

interface SubjectsWebService {

    @GET("content/grade")
    suspend fun fetchSubjects(): APIResponse
}