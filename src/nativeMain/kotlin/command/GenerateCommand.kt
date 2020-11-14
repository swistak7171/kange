package command

import com.github.ajalt.clikt.completion.CompletionCandidates
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import file.FileHelper
import parser.LogParser
import util.CommandHelper
import util.execute
import util.tryOrNull

class GenerateCommand : CliktCommand(
    help = "Generate changelog",
    name = "generate"
) {
    private val parser: LogParser = LogParser()
    private val gitStatusCommand: String = "git log --oneline --no-abbrev --date=short --pretty=format:${LogParser.GIT_LOG_FORMAT}"
    private val defaultOutputFilename: String = "CHANGELOG.md"

    val path: String? by option(
        names = arrayOf(
            "-p",
            "--path"
        ),
        help = "Git directory path",
        completionCandidates = CompletionCandidates.Path
    )

    val outputPath: String? by option(
        names = arrayOf(
            "-o",
            "--output"
        ),
        help = "Output file directory path",
        completionCandidates = CompletionCandidates.Path
    )

    val outputFilename: String? by option(
        names = arrayOf(
            "-f",
            "--filename"
        ),
        help = "Output file name"
    )

    override fun run() {
        val command = if (path != null) {
            CommandHelper.chain("cd $path", gitStatusCommand)
        } else {
            gitStatusCommand
        }

        val result = tryOrNull {
            execute(command)
        } ?: error("Specified directory is not a Git repository")

        val entries = parser.parse(result)
        val changelog = parser.createChangelog(entries)

        val filename = outputFilename ?: defaultOutputFilename
        FileHelper.write(filename, changelog)
        echo(FileHelper.read(filename))
    }
}