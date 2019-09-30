package models;

import java.util.Objects;

public class News {
    private String news;
    private int departId;
    private int id;

    public News(String news, int departId) {
        this.news = news;
        this.departId = departId;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        News news1 = (News) obj;
        return departId == news1.departId &&
                id == news1.id &&
                Objects.equals(news, news1.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(news, departId, id);
    }
}
