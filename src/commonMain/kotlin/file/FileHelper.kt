package file

expect object FileHelper {
    fun write(filename: String, content: String)
    fun read(filename: String): String
}