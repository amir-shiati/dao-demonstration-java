package core.dao;

import core.database.DBConnection;
import core.exception.EntityNotFoundException;
import core.model.Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoteDAOImpl implements DAO<Note> {

    @Override
    public Optional<Note> get(long id) throws SQLException {
        String sql = "SELECT * FROM notes WHERE id = ?";

        try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        return Optional.ofNullable(new Note(rs.getLong("id"), rs.getString("title"), rs.getString("description")));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Note> getAll() throws SQLException {
        List<Note> result = new ArrayList<Note>();
        String sql = "SELECT * FROM notes";

        try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        result.add(new Note(rs.getLong("id"), rs.getString("title"), rs.getString("description")));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public long insert(Note note) throws SQLException {
        String sql = "INSERT INTO notes (title, description) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getDescription());

            int rowsEffected = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rowsEffected == 1) {
                while (rs.next())
                    return rs.getLong(1);
            }

        }
        return -1;
    }

    @Override
    public void update(Note note) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE notes SET title = ? , description = ? WHERE id = ?";
        try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getDescription());
            ps.setLong(3, note.getId());

            int rowsEffected = ps.executeUpdate();
            if (rowsEffected < 1)
                throw new EntityNotFoundException("Note was not found!");

        }
    }

    @Override
    public void delete(Note note) throws SQLException, EntityNotFoundException {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            ps.setLong(1, note.getId());

            int rowsEffected = ps.executeUpdate();
            if (rowsEffected < 1)
                throw new EntityNotFoundException("Note was not found!");

        }
    }
}
