package ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.use_case

import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.Note
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
    ) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}