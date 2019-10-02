package com.codecool.web.servlet;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.dao.database.DbPoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemsService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/poem")
public class PoemServlet extends AbstractServlet {


    // https://www.postgresql.org/docs/current/static/errcodes-appendix.html
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = getConnection(request.getServletContext())){
            PoemDao poemDao = new DbPoemDao(connection);
            PoemsService poemsService = new SimplePoemService(poemDao);

            String id = request.getParameter("id");

            Poem poem = poemsService.getPoem(id);
            sendMessage(response, HttpServletResponse.SC_OK, poem);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
