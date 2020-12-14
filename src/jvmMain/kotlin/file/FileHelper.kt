package file

import java.io.File

actual object FileHelper {
    actual fun write(filename: String, content: String) {
        val file = File(filename)
        file.writeText(content)
    }

    actual fun read(filename: String): String {
        val file = File(filename)

        return file.readText()
    }
}