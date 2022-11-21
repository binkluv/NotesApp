package ru.mmnigmatullov.notesappmvvmv2.feature_note.presentation.notes

import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.Note
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
