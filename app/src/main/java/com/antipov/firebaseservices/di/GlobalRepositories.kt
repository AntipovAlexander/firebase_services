package com.antipov.firebaseservices.di

import com.antipov.firebaseservices.data.repository.ReactiveRepository
import com.antipov.firebaseservices.data.repository.impl.ReactiveRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GlobalRepositories {

    @Provides
    @Singleton
    fun provideRouter(): ReactiveRepository = ReactiveRepositoryImpl()

}