package com.codecool.web.dao.database;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbPoemDao extends  AbstractDao implements PoemDao {


    public DbPoemDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Poem> findAll() throws SQLException {
        List<Poem> poems = new ArrayList<>();
        String sql = "Select id, title, content FROM poems";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                poems.add(fetchPoems(resultSet));
            }
        }
        return poems;
    }

    @Override
    public List<Poem> findAllByPoetId(int poetId) throws SQLException {
        List<Poem> poems = new ArrayList<>();
        String sql = "select p.id, p.title, p.content from poems \tas p JOIN poets as c on p.id = c.id where c.id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, String.valueOf(poetId));
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    poems.add(fetchPoems(resultSet));
                }
            }
        }
        return poems;
    }

    @Override
    public Poem findById(int id) throws SQLException {
        String sql = "SELECT id, title, content FROM poems WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return fetchPoems(resultSet);
                }
            }
        }
        return null;


    }

    @Override
    public Poem add(String title, String content) throws SQLException {
        if(title == null ||"".equals(title)){
            throw new IllegalArgumentException("Nem lehet üres");
        }
        if(content == null || "".equals(content)){
            throw new IllegalArgumentException("Nem lehet üres");
        }
        boolean autoComit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO poems (title, content) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            statement.setString(1, title);
            statement.setString(2, content);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new Poem(id, title, content);
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(autoComit);
        }

    }

    private Poem fetchPoems(ResultSet resultSet) throws  SQLException{
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        return new Poem(id, title, content);
    }
}
