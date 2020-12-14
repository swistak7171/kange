package settings

import com.russhwolf.settings.Settings

actual class SettingsFactory : Settings.Factory {
    override fun create(name: String?): Settings {
        return LinuxSettings.Factory()
            .create(name ?: "settings")
    }
}