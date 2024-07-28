package org.hoods.database

import Graph
import android.app.Application

class NoteApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provideNoteRepository(getDatabaseBuilder(this))
    }
}