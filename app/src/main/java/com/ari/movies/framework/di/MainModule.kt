package com.ari.movies.framework.di

import com.ari.movies.framework.core.API_KEY
import com.ari.movies.framework.core.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Inject
    @Named("api_key")
    fun provideApiKeyOfApi(): String = API_KEY

    @Provides
    @Inject
    @Named("base_url")
    fun provideBaseUrl(): String = BASE_URL

}