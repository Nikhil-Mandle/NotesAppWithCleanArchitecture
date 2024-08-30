package com.nikhilproject.notesappcleanarchitecture.domain.repository

import com.nikhilproject.notesappcleanarchitecture.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insert(note: Note)

    suspend fun update(note: Note): Int

    suspend fun delete(note: Note): Int

    fun getAllNotes(): Flow<List<Note>>
}