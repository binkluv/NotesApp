package ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
