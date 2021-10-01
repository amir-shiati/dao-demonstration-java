package core.dao;

import core.model.Note;

import java.util.List;

public interface NoteDAO {

    // get a single note by id.
    Note getNote(long id);

    // get all notes.
    List<Note> getNotes();

    // insert a new note.
    void insertNote(Note note);

    // update an existing note.
    void updateNote(Note note);

    // delete a note.
    void deleteNote(Note note);
}
