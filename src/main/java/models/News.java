package models;

import java.util.Objects;

public class News {

    private int departId;
    private String content;
    private String header;
    private int id;

    public News(int departId, String content, String header) {
        this.departId = departId;
        this.content = content;
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return departId == news.departId &&
                id == news.id &&
                Objects.equals(content, news.content) &&
                Objects.equals(header, news.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departId, content, header, id);
    }
}
