package utils

import android.content.Context
import storage.ChatDatabase

object ChatProcessor {
    fun getChatInputForModel(context: Context): String {
        val dao = ChatDatabase.getInstance(context).chatDao()
        val messages = dao.getAll()

        // Format as plain input (e.g., last 10 messages)
        return messages.take(10)
            .joinToString("\n") { "[${it.timestamp}] ${it.text}" }
    }
}
