package com.example.mypetproject.data.repositories

import com.example.mypetproject.data.core.Database
import com.example.mypetproject.data.core.UlessonWebFactory
import com.example.mypetproject.data.repositories.cache.*
import com.example.mypetproject.data.repositories.remote.SubjectsWebService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: SubjectsRepositoryImpl): SubjectsRepository

    @Binds
    @Singleton
    fun bindLocalRepo(repository: SubjectsLocalRepositoryImpl): SubjectsLocalRepository


    @Binds
    @Singleton
    fun bindLessonsRepository(repository: LessonsRepositoryImpl): LessonsRepository

    @Binds
    @Singleton
    fun bindLessonsLocalRepo(repository: LessonsLocalRepositoryImpl): LessonsLocalRepository



}

@Module
@InstallIn(ApplicationComponent::class)
object Providers {

    @Provides
    @Singleton
    fun provideSubjectsWebService(@UlessonWebFactory retrofit: Retrofit): SubjectsWebService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideSubjectsDao(database: Database): SubjectsDao {
        return database.subjectsDao()
    }

    @Provides
    @Singleton
    fun provideLessonsDao(database: Database): LessonsDao {
        return database.lessonsDao()
    }

}