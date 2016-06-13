package com.epam.ali.task2.dao;

import com.epam.ali.task2.model.News;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class NewsDaoImpl implements NewsDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> getList() throws DaoException {
        return entityManager.createQuery("from News ", News.class).getResultList();
    }

    @Override
    public News save(News newsMessage) throws DaoException {
        return entityManager.merge(newsMessage);
    }

    @Override
    public void remove(Long id) throws DaoException {
        News newsToDelete = entityManager.find(News.class, id);
        entityManager.remove(newsToDelete);
    }

    @Override
    public News fetchById(Long id) throws DaoException {
        return entityManager.find(News.class, id);
    }

    @Override
    public void update(News newsMessage) throws DaoException {
        save(newsMessage);
    }
}
