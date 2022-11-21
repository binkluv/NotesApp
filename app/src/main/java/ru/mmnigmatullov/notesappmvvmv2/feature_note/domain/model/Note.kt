package ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mmnigmatullov.notesappmvvmv2.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,

    @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors = listOf(ElectricBlue, LightViolet, AquamarineGreen, RedPink, LightYellow)
    }
}

class InvalidNoteException(message: String): Exception(message)
