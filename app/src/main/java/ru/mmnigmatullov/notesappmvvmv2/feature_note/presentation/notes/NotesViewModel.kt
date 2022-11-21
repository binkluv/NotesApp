package ru.mmnigmatullov.notesappmvvmv2.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model.Note
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.use_case.NoteUseCase
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util.NoteOrder
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util.OrderType
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel() {

    private val _state = mutableStateOf<NotesState>(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesUseCaseJob: Job? = null

    init {
        getNotesUseCase(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (
                    state.value.noteOrder::class ==
                    event.noteOrder::class &&
                    state.value.noteOrder.orderType ==
                    event.noteOrder.orderType
                ) {
                    return
                }
                getNotesUseCase(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotesUseCase(noteOrder: NoteOrder) {
        getNotesUseCaseJob?.cancel()
        getNotesUseCaseJob = noteUseCase.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}

