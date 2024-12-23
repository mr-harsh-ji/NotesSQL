package com.example.notessqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val content = binding.contentEditText.text.toString().trim()

            // Validation logic
            if (title.isEmpty()) {
                binding.titleEditText.error = "Title is required"
                binding.titleEditText.requestFocus()
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                binding.contentEditText.error = "Content is required"
                binding.contentEditText.requestFocus()
                return@setOnClickListener
            }

            val note = Note(0, title, content)
            db.insertNote(note)

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
            finish() // Close the activity after saving the note
        }
    }
}