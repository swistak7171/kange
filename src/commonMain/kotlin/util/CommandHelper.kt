package util

expect fun getCommandSeparator(): String

object CommandHelper {
    fun chain(vararg commands: String): String {
        val separator = getCommandSeparator()

        return buildString {
            commands.forEach { command ->
                append(command)
                append(" ")
                append(separator)
                append(" ")
            }
        }.dropLast(3)
    }
}