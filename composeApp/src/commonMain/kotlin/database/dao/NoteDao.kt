package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface  NoteDao {
    @Query("Select * From note_table")
    fun getAll():Flow<List<Note>>

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}