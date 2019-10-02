package com.codecool.web.service;

import com.codecool.web.model.Poem;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface PoemsService {

    List<Poem> getPoems() throws SQLException;

    Poem getPoem(String id) throws SQLException, ServiceException;

    Poem addPoem(String s, String title) throws SQLException, ServiceException;

}
