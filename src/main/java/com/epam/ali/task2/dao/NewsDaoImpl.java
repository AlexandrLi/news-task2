package com.epam.ali.task2.dao;

import com.epam.ali.task2.model.News;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class NewsDaoImpl implements NewsDao {
    private EntityManagerFactory entityManagerFactory;


    public NewsDaoImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("news");
    }

    @Override
    public List<News> getList() throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            List<News> newsList = entityManager.createQuery("from News ", News.class).getResultList();
            tx.commit();
            return newsList;
        } catch (Exception e) {
            tx.rollback();
            throw new DaoException("Can not get all news from db", e);
        }
    }

    @Override
    public News save(News newsMessage) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            News newsFromDb = entityManager.merge(newsMessage);
            tx.commit();
            return newsFromDb;
        } catch (Exception e) {
            tx.rollback();
            throw new DaoException("Can not save newsMessage in db", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            News newsToDelete = entityManager.find(News.class, id);
            entityManager.remove(newsToDelete);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new DaoException("Can not remove newsMessage from db", e);
        }
    }

    @Override
    public News fetchById(Long id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            News news = entityManager.find(News.class, id);
            tx.commit();
            return news;
        } catch (Exception e) {
            tx.rollback();
            throw new DaoException("Can not find newsMessage with id = " + id + " in db", e);
        }
    }

    @Override
    public void update(News newsMessage) throws DaoException {
        save(newsMessage);
    }
}
