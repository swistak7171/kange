package util

import kotlin.native.Platform.osFamily

object CommandHelper {
    fun chain(vararg commands: String): String {
        val separator = when (osFamily) {
            OsFamily.WINDOWS -> " & "
            else -> " ; "
        }
        return buildString {
            commands.forEach { command ->
                append(command)
                append(separator)
            }
        }.dropLast(1)
    }
}