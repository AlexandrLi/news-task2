package com.epam.ali.task2.service;

import com.epam.ali.task2.dao.NewsDaoImpl;
import com.epam.ali.task2.model.News;
import com.epam.ali.task2.presentation.form.NewsForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsService {

    public List<News> getNewsList() {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        return newsDao.getList();
    }

    public void addNews(NewsForm newsForm) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        String title = newsForm.getTitle();
        String brief = newsForm.getBrief();
        String content = newsForm.getContent();
        News news = new News(title, new Date(), brief, content);
        newsDao.save(news);
    }

    public News getNews(long id) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        return newsDao.fetchById(id);
    }

    public void removeNews(Long id) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        newsDao.remove(id);
    }

    public void updateNews(NewsForm newsForm) throws ParseException {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        News news = newsForm.getNewsMessage();
        news.setTitle(newsForm.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        news.setDate(dateFormat.parse(newsForm.getDate()));
        news.setBrief(newsForm.getBrief());
        news.setContent(newsForm.getContent());
        newsDao.update(news);
    }
}
