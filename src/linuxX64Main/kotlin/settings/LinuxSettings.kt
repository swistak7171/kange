package settings

import com.russhwolf.settings.Settings
import configuration.Configuration
import file.FileHelper
import util.toBooleanOrNull

class LinuxSettings(
    private val filename: String,
) : Settings {

    class Factory : Settings.Factory {
        override fun create(name: String?): Settings {
            return LinuxSettings("\$HOME/.${Configuration.name}/$name.properties")
        }
    }

    private fun readMap(): Map<String, String> {
        val content = FileHelper.read(filename)

        return content.split("\n")
            .filter { line ->
                line.isNotBlank() || line.split("=").size == 2
            }
            .map { line ->
                val keyValue = line.split("=")
                keyValue.first() to keyValue.last()
            }
            .toMap()
    }

    private fun serializeMap(map: Map<String, String>): String {
        return buildString {
            map.forEach { entry ->
                append(entry.key)
                append("=")
                append(entry.value)
            }
        }
    }

    private fun saveMap(map: Map<String, String>) {
        val content = serializeMap(map)
        FileHelper.write(filename, content)
    }

    private inline fun <reified T> getValueOrDefault(key: String, defaultValue: T?): T? {
        val value = readMap()[key] ?: return defaultValue

        return when (T::class) {
            Boolean::class -> value.toBooleanOrNull()
            Double::class -> value.toDoubleOrNull()
            Float::class -> value.toFloatOrNull()
            Int::class -> value.toIntOrNull()
            Long::class -> value.toLongOrNull()
            else -> value
        } as T ?: defaultValue
    }

    private fun <T> setValue(key: String, value: T) {
        val map = readMap().toMutableMap()
        map[key] = value.toString()
        saveMap(map)
    }

    override val keys: Set<String>
        get() {
            return readMap()
                .map { it.key }
                .toSet()
        }

    override val size: Int
        get() = readMap().size

    override fun clear() {
        FileHelper.write(filename, "")
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getValueOrDefault(key, defaultValue)!!

    override fun getBooleanOrNull(key: String): Boolean? =
        getValueOrDefault(key, null)

    override fun getDouble(key: String, defaultValue: Double): Double =
        getValueOrDefault(key, defaultValue)!!

    override fun getDoubleOrNull(key: String): Double? =
        getValueOrDefault(key, null)

    override fun getFloat(key: String, defaultValue: Float): Float =
        getValueOrDefault(key, defaultValue)!!

    override fun getFloatOrNull(key: String): Float? =
        getValueOrDefault(key, null)

    override fun getInt(key: String, defaultValue: Int): Int =
        getValueOrDefault(key, defaultValue)!!

    override fun getIntOrNull(key: String): Int? =
        getValueOrDefault(key, null)

    override fun getLong(key: String, defaultValue: Long): Long =
        getValueOrDefault(key, defaultValue)!!

    override fun getLongOrNull(key: String): Long? =
        getValueOrDefault(key, null)

    override fun getString(key: String, defaultValue: String): String =
        getValueOrDefault(key, defaultValue)!!

    override fun getStringOrNull(key: String): String? =
        getValueOrDefault(key, null)

    override fun hasKey(key: String): Boolean =
        readMap().contains(key)

    override fun putBoolean(key: String, value: Boolean) {
        setValue(key, value)
    }

    override fun putDouble(key: String, value: Double) {
        setValue(key, value)
    }

    override fun putFloat(key: String, value: Float) {
        setValue(key, value)
    }

    override fun putInt(key: String, value: Int) {
        setValue(key, value)
    }

    override fun putLong(key: String, value: Long) {
        setValue(key, value)
    }

    override fun putString(key: String, value: String) {
        setValue(key, value)
    }

    override fun remove(key: String) {
        val map = readMap().toMutableMap().apply {
            remove(key)
        }
        saveMap(map)
    }
}