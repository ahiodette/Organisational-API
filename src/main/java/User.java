

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class User {
    private String username;
    private String role;
    private String post;
    private int id;
    private String departid;

    public User(String username, String role, String post, String departid) {
        this.username = username;
        this.role = role;
        this.post = post;
        this.departid = departid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setPost(String post) {
        this.post = post;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                departid == user.departid &&
                Objects.equals(username, user.username) &&
                Objects.equals(role, user.role) &&
                Objects.equals(post, user.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role, post, id, departid);
    }


    public void add(User user) {
        String sql = "INSERT INTO users (username,post,departid,role) VALUES (:username,:post,:departid,:role);";

        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("username", this.username)
                    .addParameter("post", this.post)
                    .addParameter("departid", this.departid)
                    .addParameter("role", this.role)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<User> getAll() {
        String sql = "SELECT * FROM users";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }
}
