package ru.mmnigmatullov.notesappmvvmv2.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mmnigmatullov.notesappmvvmv2.feature_note.data.data_source.NoteDatabase
import ru.mmnigmatullov.notesappmvvmv2.feature_note.data.repository.NoteRepositoryImpl
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.repository.NoteRepository
import ru.mmnigmatullov.notesappmvvmv2.feature_note.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNote = GetNote(repository)
        )
    }
}