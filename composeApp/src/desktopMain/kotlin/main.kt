import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DatabaseTutorial",
    ) {
        val databaseBuilder = getDesktopDatabaseBuilder()
        Graph.provideNoteRepository(databaseBuilder)
        App()
    }
}