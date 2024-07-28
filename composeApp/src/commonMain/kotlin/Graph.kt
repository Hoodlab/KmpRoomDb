import androidx.room.RoomDatabase
import database.AppDatabase
import database.getRoomDatabase
import repository.NoteRepository

object Graph {
    lateinit var  repository: NoteRepository
    fun provideNoteRepository(databaseBuilder:RoomDatabase.Builder<AppDatabase>){
        val database = getRoomDatabase(databaseBuilder)
        repository = NoteRepository(database.noteDao())
    }
}