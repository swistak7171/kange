package parser

import model.DateEntry
import model.Entry

class LogParser {
    fun parse(text: String): List<DateEntry> {
        return text.lineSequence()
            .filter { it.isNotBlank() }
            .map { line ->
                val parts = line.split(
                    delimiters = arrayOf(";"),
                    limit = 3
                )

                Entry(parts[0], parts[1], parts[2])
            }
            .groupBy { it.date }
            .map { DateEntry(it.key, it.value) }
            .toList()
    }

    companion object {
        private const val DELIMITER: Char = ';'
        const val GIT_LOG_FORMAT: String = "%H$DELIMITER%ad$DELIMITER%B"
    }
}