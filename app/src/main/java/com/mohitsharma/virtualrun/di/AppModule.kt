package com.mohitsharma.virtualrun.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohitsharma.virtualrun.db.RunDAO
import com.mohitsharma.virtualrun.db.RunDatabase
import com.mohitsharma.virtualrun.others.Constants.RUN_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDatabase(
        @ApplicationContext appContext:Context
    ) = Room.databaseBuilder(
        appContext,
        RunDatabase::class.java,
        RUN_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db:RunDatabase) = db.getRunDao()
}