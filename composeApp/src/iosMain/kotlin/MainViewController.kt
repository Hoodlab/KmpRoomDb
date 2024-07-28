import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    Graph.provideNoteRepository(getIosDatabaseBuilder())
    App()
}