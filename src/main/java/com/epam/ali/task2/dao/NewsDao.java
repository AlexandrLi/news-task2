package com.epam.ali.task2.dao;

import com.epam.ali.task2.model.News;

import java.util.List;

public interface NewsDao {
    List<News> getList() throws DaoException;

    News save(News newsMessage) throws DaoException;

    void remove(Long id) throws DaoException;

    News fetchById(Long id) throws DaoException;

    void update(News newsMessage) throws DaoException;
}
