package com.epam.ali.task2.service;

import com.epam.ali.task2.dao.NewsDaoImpl;
import com.epam.ali.task2.model.News;
import com.epam.ali.task2.presentation.form.NewsForm;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsService {

    private NewsDaoImpl newsDao;

    public NewsService(NewsDaoImpl newsDao) {
        this.newsDao = newsDao;
    }

    public NewsService() {

    }

    public List<News> getNewsList() {
        return newsDao.getList();
    }

    @Transactional
    public void addNews(NewsForm newsForm) {
        String title = newsForm.getTitle();
        String brief = newsForm.getBrief();
        String content = newsForm.getContent();
        News news = new News(title, new Date(), brief, content);
        newsDao.save(news);
    }

    public News getNews(long id) {
        return newsDao.fetchById(id);
    }

    @Transactional
    public void removeNews(Long id) {
        newsDao.remove(id);
    }

    @Transactional
    public void updateNews(NewsForm newsForm) throws ParseException {
        News news = newsForm.getNewsMessage();
        news.setTitle(newsForm.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        news.setDate(dateFormat.parse(newsForm.getDate()));
        news.setBrief(newsForm.getBrief());
        news.setContent(newsForm.getContent());
        newsDao.update(news);
    }
}
