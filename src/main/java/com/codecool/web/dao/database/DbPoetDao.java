package com.codecool.web.dao.database;

import com.codecool.web.dao.PoetDao;
import com.codecool.web.model.Poet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DbPoetDao extends AbstractDao implements PoetDao {

    public DbPoetDao(Connection connection) {
        super(connection);
    }

    public List<Poet> findAll() throws SQLException{
        String sql = "SELECT id, email, password FROM poets";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            List<Poet> poets = new ArrayList<>();
            while (resultSet.next()){
                poets.add(fetchPoets(resultSet));
            }
            return poets;
        }
    }

    public Poet findByEmail (String email) throws SQLException{
        if(email == null || "".equals(email)){
            throw new IllegalArgumentException("Nem lehet nulla");
        }
        String sql = "SELECT * FROM poets WHERE email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return fetchPoets(resultSet);
                }
            }
        }
        return null;
    }

    private Poet fetchPoets(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Poet(id, email, password);
    }



}
