import androidx.room.Room
import androidx.room.RoomDatabase
import database.AppDatabase

fun getIosDatabaseBuilder():RoomDatabase.Builder<AppDatabase>{
    val dbFilePath = NSHomeDirectory() + "/my_room.db"
    return Room.databaseBuilder(
        name = dbFilePath,
        factory = {AppDatabase:class.instantiateImpl()}
    )
}