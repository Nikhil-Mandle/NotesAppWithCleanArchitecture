package com.nikhilproject.notesappcleanarchitecture.data.mapper

import com.nikhilproject.notesappcleanarchitecture.data.local.NoteEntity
import com.nikhilproject.notesappcleanarchitecture.domain.model.Note

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(id, title, desc)
}

fun NoteEntity.toNote(): Note {
    return Note(id, title, desc)
}