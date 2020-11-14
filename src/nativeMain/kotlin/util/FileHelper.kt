package util

import file.FileMode
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fputs
import platform.posix.fscanf

object FileHelper {
    fun write(filename: String, content: String) {
        val filePointer = fopen(filename, FileMode.WRITE.value)
        fputs(content, filePointer)
        fclose(filePointer)
    }

    fun read(filename: String): String {
        val content: String = ""
        val filePointer = fopen(filename, FileMode.READ.value)
        fscanf(filePointer, content)
        fclose(filePointer)

        return content
    }
}