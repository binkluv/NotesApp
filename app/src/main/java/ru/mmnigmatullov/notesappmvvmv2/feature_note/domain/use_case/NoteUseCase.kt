package ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.use_case

data class NoteUseCase(
    val  getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNote: GetNote
)
