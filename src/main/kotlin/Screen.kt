import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.InfoCmp.Capability
import org.jline.utils.NonBlockingReader

class Screen {
    constructor() {
        terminal.enterRawMode()
    }

    companion object {
        val terminal: Terminal = TerminalBuilder.builder().system(true).build()
        val reader: NonBlockingReader = terminal.reader() // NonBlockingReader

        // Clear entire screen
        fun clearScreen() {
            terminal.puts(Capability.clear_screen) // Clear screen
            terminal.puts(Capability.cursor_home)  // Move cursor to top-left
            terminal.flush()
        }

        // Move cursor to absolute position (x=col, y=row)
        fun goto(x: Int = 1, y: Int = 1) {
            terminal.puts(Capability.cursor_address, y - 1, x - 1) // 0-indexed internally
            terminal.flush()
        }

        // Overload for Pair
        fun goto(pos: Pair<Int, Int>) {
            goto(pos.first, pos.second)
        }

        // Clear current line
        fun clearLine() {
            terminal.puts(Capability.carriage_return) // Move to line start
            terminal.puts(Capability.clr_eol)         // Clear line
            terminal.flush()
        }

        /**
         * Execute system commands safely and resiliently
         */
        suspend fun executeCommand(command: String): String = withContext(Dispatchers.IO) {
            try {
                val process = ProcessBuilder(*command.split(" ").toTypedArray()).redirectErrorStream(true).start()

                val result = process.inputStream.bufferedReader().readText()
                process.waitFor()
                result.trim()
            } catch (e: Exception) {
                "Command $command failed: ${e.message}"
            }
        }
    }
}
