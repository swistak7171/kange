package parser

import model.Entry

class LogParser {
    fun parse(text: String): List<Entry> {
        return text.lineSequence()
            .filter { it.isNotBlank() }
            .map { line ->
                val parts = line.split(
                    delimiters = arrayOf(";"),
                    limit = 3
                )

                Entry(parts[0], parts[1], parts[2])
            }
            .toList()
    }

    companion object {
        private const val DELIMITER: Char = ';'
        const val GIT_LOG_FORMAT: String = "%H$DELIMITER%ad$DELIMITER%B"
    }
}