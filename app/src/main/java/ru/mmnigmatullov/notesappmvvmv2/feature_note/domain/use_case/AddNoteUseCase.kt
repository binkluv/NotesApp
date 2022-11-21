package ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.use_case

import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.InvalidNoteException
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.Note
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("Заголовок заметки не может быть пустым.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("Содержимое заметки не может быть пустым.")
        }
        repository.insertNote(note)
    }
}