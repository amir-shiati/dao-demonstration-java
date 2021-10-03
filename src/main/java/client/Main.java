package client;

import core.dao.NoteDAOImpl;
import core.exception.EntityNotFoundException;
import core.model.Note;

import java.sql.SQLException;
import java.util.List;

//just a test
public class Main {

    private static NoteDAOImpl noteDAO = new NoteDAOImpl();

    public static void main(String[] args) {
        new Main().getAllNotes();
    }

    void getANote(long id) {
        try {
            Note note = noteDAO.get(id).get();
            System.out.println(note.getTitle() + "\t" + note.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getAllNotes() {
        try {
            List<Note> notes = noteDAO.getAll();
            for (Note note : notes) {
                System.out.println(note.getTitle() + "\t" + note.getDescription());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addNote() {
        Note note = new Note("Another Note", "Description");
        try {
            long newNoteId = noteDAO.insert(note);
            if (newNoteId != -1)
                System.out.println(newNoteId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateANote(long id) {
        Note note = new Note(id, "Updated title", "Update description");
        try {
            noteDAO.update(note);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    void deleteANote(long id) {
        Note note = new Note(id);
        try {
            noteDAO.delete(note);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
