import Screen.Companion.clearScreen
import Screen.Companion.goto
import Screen.Companion.reader
import Screen.Companion.terminal
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.max

fun main() = runBlocking {
    clearScreen()
    println("Press 'q' to quit...")

    while (true) {
        val lines = """
  RAM: ${(4..15).random()}/16GB
  CPU: ${(1..100).random()}% 
  Disk: ${(50..90).random()}/100GB
"""
        // Print lines with offsets
        goto(2, 2)
        print(lines.padEnd(max(2, terminal.width - 10)))
        val ch = reader.read(100) // timeout in milliseconds
        if (ch != -1) { // only process if a key was actually pressed
            when (ch.toChar()) {
                'q' -> break
            }
        }

        // Do other tasks here, e.g., updating cockpit
        delay(500)
    }

    terminal.close()
}
