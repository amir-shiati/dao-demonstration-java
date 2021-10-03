package core.dao;

import core.exception.EntityNotFoundException;
import core.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    // get a single Object by id.
    Optional<T> get(long id) throws SQLException;

    // get all Objects.
    List<T> getAll() throws SQLException;

    // insert a new Object.
    long insert(T t) throws SQLException;

    // update an existing Object.
    void update(T t) throws SQLException, EntityNotFoundException;

    // delete an Object.
    void delete(T t) throws SQLException, EntityNotFoundException;
}
