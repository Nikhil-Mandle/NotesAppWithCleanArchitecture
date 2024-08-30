package com.nikhilproject.notesappcleanarchitecture.data.di

import android.content.Context
import com.nikhilproject.notesappcleanarchitecture.data.local.NotesDao
import com.nikhilproject.notesappcleanarchitecture.data.local.NotesDatabase
import com.nikhilproject.notesappcleanarchitecture.data.repository.NotesRepositoryImpl
import com.nikhilproject.notesappcleanarchitecture.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NotesDataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return NotesDatabase.getInstance(context)
    }

    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.getNotesDao()
    }

    @Provides
    fun provideRepository(notesDao: NotesDao): NotesRepository {
        return NotesRepositoryImpl(notesDao)
    }


}