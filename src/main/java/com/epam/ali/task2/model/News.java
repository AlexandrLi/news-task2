package com.epam.ali.task2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NEWS")
@Inheritance(strategy = InheritanceType.JOINED)
public class News extends BaseEntity {

    private static final long serialVersionUID = -6023941092261907639L;
    @Column(name = "NUMBER_TITLE")
    private String title;
    @Column(name = "NEWS_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "NEWS_BRIEF")
    private String brief;
    @Column(name = "NEWS_CONTENT")
    private String content;

    public News(String title, Date date, String brief, String content) {
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public News() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + super.getId() +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
