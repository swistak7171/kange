package command

import com.github.ajalt.clikt.completion.CompletionCandidates
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import util.CommandHelper
import util.execute
import util.tryOrNull

class GenerateCommand : CliktCommand(
    help = "Generate changelog",
    name = "generate"
) {
    private val gitStatusCommand: String = "git status"
    val path: String? by option(
        names = arrayOf(
            "-p",
            "--path"
        ),
        help = "Git directory path",
        completionCandidates = CompletionCandidates.Path
    )

    override fun run() {
        val command = if (path != null) {
            CommandHelper.chain("cd $path", gitStatusCommand)
        } else {
            gitStatusCommand
        }

        val result = tryOrNull { execute(command) }
        echo(result)
    }
}