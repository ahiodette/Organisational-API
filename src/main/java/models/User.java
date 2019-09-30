package models;

import java.util.Objects;

public class User {
    private String userName;
    private String role;
    private String post;
    private int id;
    private int departId;

    public User(String userName, String role, String post, int departId) {
        this.userName = userName;
        this.role = role;
        this.post = post;
        this.departId = departId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPost() {
        return post;
    }

    public void setPosition(String position) {
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                departId == user.departId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(role, user.role) &&
                Objects.equals(post, user.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, role, post, id, departId);
    }
}
