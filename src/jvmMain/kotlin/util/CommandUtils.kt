package util

actual fun getCommandSeparator(): String {
    val osName = System.getProperty("os.name")
    val isWindows = osName.startsWith("Windows")

    return if (isWindows) {
        "&"
    } else {
        ";"
    }
}