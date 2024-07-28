package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import database.entity.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.NoteRepository
import kotlin.reflect.KClass

class NoteViewModel(private val noteRepository: NoteRepository):ViewModel() {
    private val _noteState = MutableStateFlow(NoteState())
    val noteState = _noteState.asStateFlow()

    init {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect{ notes ->
                _noteState.update {
                    it.copy(notes =  notes)
                }
            }
        }
    }

    fun saveNote(title:String,description:String){
        viewModelScope.launch {
            noteRepository.saveNote(Note(title = title, description = description))
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

}

data class NoteState(
    val notes:List<Note> = emptyList()
)

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val noteRepository: NoteRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return NoteViewModel(noteRepository = noteRepository) as T
    }
}