package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import database.entity.Note

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: NoteViewModel) {
    val noteState by viewModel.noteState.collectAsState()
    Column(
        modifier
    ) {
        val (title, setTitle) = rememberSaveable { mutableStateOf("") }
        val (description, setDescription) = rememberSaveable { mutableStateOf("") }
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    TextField(
                        value = title,
                        onValueChange = setTitle,
                        label = { Text("Note Title") },
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = setDescription,
                        label = { Text("Note Description") },
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        )
                    )
                }
                Button(
                    onClick = { viewModel.saveNote(title, description) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Save Note")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(noteState.notes) { note ->
                    NoteItem(note = note){
                        viewModel.deleteNote(note)
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note, onNoteClick: () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onNoteClick()
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(note.title, style = MaterialTheme.typography.h6)
            Spacer(Modifier.height(4.dp))
            Text(note.description, style = MaterialTheme.typography.body2)
            Divider(Modifier.fillMaxWidth())
        }
    }
}