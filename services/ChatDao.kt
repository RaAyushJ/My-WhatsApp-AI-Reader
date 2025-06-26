package storage

import androidx.room.*

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(message: ChatMessage)

    @Query("SELECT * FROM chat_messages ORDER BY timestamp DESC")
    fun getAll(): List<ChatMessage>
}
