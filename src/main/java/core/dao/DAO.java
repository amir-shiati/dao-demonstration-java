package core.dao;

import core.model.Note;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    // get a single note by id.
    Optional<T> get(long id);

    // get all notes.
     List<T> getAll();

    // insert a new note.
     void insert(T t);

    // update an existing note.
     void update(T t);

    // delete a note.
     void delete(T t);
}
