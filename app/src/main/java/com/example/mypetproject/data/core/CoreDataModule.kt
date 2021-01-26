package com.example.mypetproject.data.core

import android.content.Context
import androidx.room.Room
import com.example.mypetproject.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

const val DATABASE_NAME = "ulesson"

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UlessonWebFactory

@Module
@InstallIn(ApplicationComponent::class)
object CoreDataModule {

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideDatabaseProvider(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
            .addMigrations() // TODO: Deal with migrations
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    @UlessonWebFactory
    fun provideUlessonWebServiceFactory(
        webServiceClient: CoreWebServiceClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        val baseClient = webServiceClient()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ULESSON_BASE_URL)
            .client(baseClient)
            .addConverterFactory(converterFactory)
            .build()
    }

}
