import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
import storage.ChatDatabase
import storage.ChatMessage

class WhatsAppAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName != "com.whatsapp") return

        val rootNode = rootInActiveWindow ?: return
        val messages = mutableListOf<String>()
        traverse(rootNode, messages)

        val dao = ChatDatabase.getInstance(this).chatDao()
        messages.forEach {
            Log.d("ChatRead", it)
            dao.insert(ChatMessage(text = it))
        }
    }

    private fun traverse(node: AccessibilityNodeInfo, messages: MutableList<String>) {
        if (node.childCount == 0 && node.text != null) {
            val text = node.text.toString()
            if (text.isNotBlank()) messages.add(text)
        } else {
            for (i in 0 until node.childCount) {
                node.getChild(i)?.let { traverse(it, messages) }
            }
        }
    }

    override fun onInterrupt() {}
}
