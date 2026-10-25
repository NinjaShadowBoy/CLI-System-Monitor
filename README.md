# CLI System Monitor

A terminal system monitor written in Kotlin. The goal is a full-screen
"cockpit" in the terminal that shows CPU, memory, disk, network, USB and audio
devices at a glance, with detail screens you switch between using single
keys. The design and UI mockups are in
[CLI-System-Monitor-Docs](https://github.com/NinjaShadowBoy/CLI-System-Monitor-Docs).

**Status: early work in progress.** What exists today:

- A refreshing terminal screen drawn with JLine (cursor positioning, clear
  screen, non-blocking key reads); press `q` to quit
- Readers for real stats from the Linux `/proc` filesystem (`/proc/cpuinfo`,
  `/proc/meminfo`, `/proc/uptime`) in `System.kt`

The main screen still shows placeholder numbers: the `/proc` readers are not
wired into it yet, and `Network.kt` and `Storage.kt` are empty.

## Tech

- Kotlin 2.2 on the JVM (Java 17 toolchain)
- kotlinx.coroutines for the refresh loop
- JLine 3 for terminal control
- kotlinx.serialization
- Gradle (wrapper included)

Reading `/proc` makes the stats Linux-only.

## Get it and build it

You need a JDK (17 or newer).

```bash
git clone https://github.com/NinjaShadowBoy/CLI-System-Monitor.git
cd CLI-System-Monitor
chmod +x gradlew          # the wrapper is not marked executable in git
./gradlew build           # on Windows: gradlew.bat build
```

## Run it

The build has no `run` task yet, so the easiest way is IntelliJ IDEA: open the
folder as a Gradle project and run `main()` in `src/main/kotlin/Main.kt`. Run it
in a real terminal for the full-screen display (for example IntelliJ's
"Emulate terminal in output console" run option).

## Code layout

```
src/main/kotlin/
├── Main.kt      main loop: draws the screen, handles keys
├── Screen.kt    terminal helpers (clear, goto, run a shell command)
├── System.kt    CPU, memory and uptime from /proc
├── Network.kt   (planned)
└── Storage.kt   (planned)
```
