package util

inline fun <T> tryOrDefault(defaultValue: T, action: () -> T): T {
    return try {
        action()
    } catch (throwable: Throwable) {
        defaultValue
    }
}

inline fun <T> tryOrNull(action: () -> T): T? =
    tryOrDefault(null, action)

inline fun <T : Any, R> T?.useOrNull(action: (T) -> R): R? =
    if (this != null) action(this) else null

inline fun String?.toBooleanOrNull(): Boolean? {
    return when (this?.toLowerCase()) {
        "true" -> true
        "false" -> false
        else -> null
    }
}