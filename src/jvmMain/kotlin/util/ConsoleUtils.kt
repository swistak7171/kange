package util

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.text.buildString

actual fun execute(command: String): String {
    val processBuilder = ProcessBuilder(command).apply {
        redirectErrorStream(true)
    }

    val process = processBuilder.start()
    val streamReader = InputStreamReader(process.inputStream)
    val reader = BufferedReader(streamReader)
    val output = reader.use {
        buildString {
            var line = reader.readLine()
            while (line != null) {
                append(line)
                line = reader.readLine()
            }
        }
    }

    process.waitFor()
    reader.close()

    return output
}