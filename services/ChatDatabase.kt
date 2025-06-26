package storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChatMessage::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        private var INSTANCE: ChatDatabase? = null
        fun getInstance(context: Context): ChatDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java, "chat_db"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}
