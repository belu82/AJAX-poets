package com.codecool.web.service.simple;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemsService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimplePoemService implements PoemsService {

    private  final PoemDao poemDao;

    public SimplePoemService(PoemDao poemDao) {
        this.poemDao = poemDao;
    }

    @Override
    public List<Poem> getPoems() throws SQLException {
        return poemDao.findAll();
    }

    @Override
    public Poem getPoem(String id) throws SQLException, ServiceException {
        try{
            Poem poem = poemDao.findById(Integer.parseInt(id));
            if(poem == null){
                throw new ServiceException("unkonw poem");
            }
            return poem;
        }catch (NumberFormatException ex){
            throw new ServiceException("Poem id must be integer");
        }catch (IllegalArgumentException e){
            throw new ServiceException(e.getMessage());
        }
    }



    @Override
    public Poem addPoem(String title, String content) throws SQLException, ServiceException {
        try {
            return poemDao.add(title, content);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
