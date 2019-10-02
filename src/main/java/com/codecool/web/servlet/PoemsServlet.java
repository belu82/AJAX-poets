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
import java.util.List;

@WebServlet("/protected/poems")
public class PoemsServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = getConnection(request.getServletContext())){
            PoemDao poemDao = new DbPoemDao(connection);
            PoemsService poemsService = new SimplePoemService(poemDao);

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Poem poem = poemsService.addPoem(title, content);
            sendMessage(response, HttpServletResponse.SC_OK, poem);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection = getConnection(request.getServletContext())){
            PoemDao poemDao = new DbPoemDao(connection);
            PoemsService poemsService = new SimplePoemService(poemDao);

            List<Poem> poems = poemsService.getPoems();
            sendMessage(response, HttpServletResponse.SC_OK, poems);
        } catch (SQLException e) {
            handleSqlError(response, e);
        }
    }
}
