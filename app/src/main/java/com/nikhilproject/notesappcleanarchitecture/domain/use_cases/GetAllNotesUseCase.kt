package com.nikhilproject.notesappcleanarchitecture.domain.use_cases

import com.nikhilproject.notesappcleanarchitecture.domain.repository.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    operator fun invoke() = notesRepository.getAllNotes()
}