import androidx.room.Room
import androidx.room.RoomDatabase
import database.AppDatabase
import java.io.File

fun getDesktopDatabaseBuilder():RoomDatabase.Builder<AppDatabase>{
    val dbFile = File(System.getProperty("java.io.tmpdir"),"my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath
    )
}