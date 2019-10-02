package com.codecool.web.dao;

import com.codecool.web.model.Poem;

import java.sql.SQLException;
import java.util.List;

public interface PoemDao {

    List<Poem> findAll() throws SQLException;
    List<Poem> findAllByPoetId(int poetId) throws SQLException;

    Poem findById (int id) throws SQLException;
    Poem add(String title, String content) throws SQLException;
}
