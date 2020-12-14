package settings

import com.russhwolf.settings.JvmPreferencesSettings
import com.russhwolf.settings.Settings

actual class SettingsFactory : Settings.Factory {
    override fun create(name: String?): Settings {
        return JvmPreferencesSettings.Factory()
            .create(name)
    }
}