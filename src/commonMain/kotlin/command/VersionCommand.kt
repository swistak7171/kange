package command

import com.github.ajalt.clikt.core.CliktCommand
import configuration.Configuration

class VersionCommand : CliktCommand(
    help = "Show application version",
    name = "version"
) {
    override fun run() {
        echo("kange version: ${Configuration.version}")
    }
}