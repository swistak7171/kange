package file

enum class FileMode(
    val value: String,
) {
    READ("r"),
    WRITE("w"),
    READ_WRITE("w+"),
}