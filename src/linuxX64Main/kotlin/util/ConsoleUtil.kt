package util

import FileMode
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.fgets
import platform.posix.pclose
import platform.posix.popen

actual fun execute(
    command: String
): String {
    val outputPointer = popen(command, FileMode.READ.value) ?: error("Failed to run command: $command")
    val output = buildString {
        val buffer = ByteArray(4096)
        while (true) {
            val input = fgets(buffer.refTo(0), buffer.size, outputPointer) ?: break
            append(input.toKString())
        }
    }

    val status = pclose(outputPointer)
    if (status != 0) {
        error("Command `$command` failed with status $status")
    }

    return output
}