package utils

import android.view.accessibility.AccessibilityNodeInfo

object NodeParser {
    fun getAllTextFromNode(node: AccessibilityNodeInfo?): List<String> {
        val texts = mutableListOf<String>()
        if (node == null) return texts

        if (node.childCount == 0 && node.text != null) {
            texts.add(node.text.toString())
        } else {
            for (i in 0 until node.childCount) {
                texts.addAll(getAllTextFromNode(node.getChild(i)))
            }
        }
        return texts
    }
}
