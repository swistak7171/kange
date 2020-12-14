package settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.WindowsSettings

actual class SettingsFactory : Settings.Factory {
    override fun create(name: String?): Settings {
        return WindowsSettings.Factory("KANGE")
            .create(name)
    }
}