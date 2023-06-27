package com.asig.casco.api

import android.content.Context
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.security.UserDataStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTokenStorage(@ApplicationContext context: Context): UserDataStorage {
        return UserDataStorage(context)
    }

    @Provides
    fun provideRetrofitFactory(): RetrofitFactory {
        return RetrofitFactory()
    }
}