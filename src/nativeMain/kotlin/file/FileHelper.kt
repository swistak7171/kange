package file

import platform.posix.*

actual object FileHelper {
    actual fun write(filename: String, content: String) {
        val filePointer = fopen(filename, FileMode.WRITE.value)
        fputs(content, filePointer)
        fclose(filePointer)
    }

    actual fun read(filename: String): String {
        val filePointer = fopen(filename, FileMode.READ.value)

        return buildString {
            while (true) {
                val character = getc(filePointer)
                if (character == EOF) {
                    fclose(filePointer)
                    break
                } else {
                    append(character.toChar())
                }
            }

        }
    }
}