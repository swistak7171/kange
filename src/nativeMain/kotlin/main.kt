
import com.github.ajalt.clikt.core.subcommands
import command.GenerateCommand
import command.MainCommand

fun main(args: Array<String>) {
    MainCommand().subcommands(
        GenerateCommand()
    ).main(args)
}