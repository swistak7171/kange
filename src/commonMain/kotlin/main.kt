
import com.github.ajalt.clikt.core.subcommands
import command.GenerateChangelogCommand
import command.MainCommand
import command.VersionCommand
import configuration.Configuration

fun main(args: Array<String>) {
    println("Welcome to ${Configuration.name}, your best changelog generator!")

    MainCommand().subcommands(
        VersionCommand(),
        GenerateChangelogCommand()
    ).main(args)
}