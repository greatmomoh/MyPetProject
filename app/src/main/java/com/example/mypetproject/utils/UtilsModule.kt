package com.example.mypetproject.utils

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UtilsModule {

    @Binds
    abstract fun bindsPostExecutionThread(postExecutionThread: PostExecutionThreadImpl): PostExecutionThread

}
