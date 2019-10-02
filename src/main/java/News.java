
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class News {

    private int depid;
    private String content;
    private String header;
    private int id;

    public News(int departid, String content, String header) {
        this.depid = departid;
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
        return depid;
    }

    public void setDepartId(int departId) {
        this.depid = depid;
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
        return depid == news.depid &&
                id == news.id &&
                Objects.equals(content, news.content) &&
                Objects.equals(header, news.header);
    }


    public void add(News news) {
        String sql = "INSERT INTO news (depid, content, header) VALUES (:depid, :content, :header)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("depid", this.depid)
                    .addParameter("content", this.content)
                    .addParameter("header", this.header)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(depid, content, header, id);
    }



    public static List<News> getAll() {
        String sql = "SELECT * FROM news";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    public List<Department> getAllNewsForDepts(int newsId) {
        List<Department> departments = new ArrayList();
        String joinQuery = "SELECT * FROM news WHERE deptid = :deptid";

        try (Connection con = DB.sql2o.open()) {
            List<Integer> allDepartmentIds = con.createQuery(joinQuery)
                    .addParameter("newsId", newsId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departmentId : allDepartmentIds){
                String departmentQuery = "SELECT * FROM departments WHERE id = :deptId";
                departments.add(
                        con.createQuery(departmentQuery)
                                .addParameter("deptId", departmentId)
                                .executeAndFetchFirst(Department.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    public List<Department> getAllDeptsForNews(int newsId) {
        List<Department> departments = new ArrayList();
        String joinQuery = "SELECT deptId FROM news WHERE newsid = :newsid";

        try (Connection con = DB.sql2o.open()) {
            List<Integer> allDepartmentIds = con.createQuery(joinQuery)
                    .addParameter("newsId", newsId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departmentId : allDepartmentIds){
                String departmentQuery = "SELECT * FROM departments WHERE id = :deptId";
                departments.add(
                        con.createQuery(departmentQuery)
                                .addParameter("deptId", departmentId)
                                .executeAndFetchFirst(Department.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

}
