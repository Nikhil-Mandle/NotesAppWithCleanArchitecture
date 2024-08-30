package com.nikhilproject.notesappcleanarchitecture.domain.use_cases

import com.nikhilproject.notesappcleanarchitecture.domain.model.Note
import com.nikhilproject.notesappcleanarchitecture.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note) = notesRepository.delete(note)
}