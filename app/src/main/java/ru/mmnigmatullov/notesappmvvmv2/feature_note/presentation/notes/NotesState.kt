package ru.mmnigmatullov.notesappmvvmv2.feature_note.presentation.notes

import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.Note
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util.NoteOrder
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
