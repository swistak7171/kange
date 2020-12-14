package util

import kotlin.native.Platform.osFamily

actual fun getCommandSeparator(): String {
    return if (osFamily == OsFamily.WINDOWS) {
        "&"
    } else {
        ";"
    }
}