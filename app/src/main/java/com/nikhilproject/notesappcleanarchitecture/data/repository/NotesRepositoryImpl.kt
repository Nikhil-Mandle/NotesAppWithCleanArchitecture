package com.nikhilproject.notesappcleanarchitecture.data.repository

import com.nikhilproject.notesappcleanarchitecture.data.local.NotesDao
import com.nikhilproject.notesappcleanarchitecture.data.mapper.toNote
import com.nikhilproject.notesappcleanarchitecture.data.mapper.toNoteEntity
import com.nikhilproject.notesappcleanarchitecture.domain.model.Note
import com.nikhilproject.notesappcleanarchitecture.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {
    override suspend fun insert(note: Note) {
        notesDao.insert(note.toNoteEntity())
    }

    override suspend fun update(note: Note): Int {
        return notesDao.update(note.toNoteEntity())
    }

    override suspend fun delete(note: Note): Int {
        return notesDao.delete(note.toNoteEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> =
        notesDao.getAllNotes().map { it ->
            it.map { it.toNote() }
        }
}