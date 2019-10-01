package dao;

import models.DB;
import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao  {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

//    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (name) VALUES (:name)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

//    @Override
    public List<News> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }

    }

//    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id";
        String deleteJoin = "DELETE from department_news WHERE newsid = :newsid";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("newsid", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
    public void clearAll() {
        String sql = "DELETE from news";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

//    @Override
    public void addNewsToDept(News news, Department department){
        String sql = "INSERT INTO department_news (deptId, newsid) VALUES (:deptId, :newsId)";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("deptId", department.getDepId())
                    .addParameter("newsid", news.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
    public List<Department> getAllDeptsForNews(int newsId) {
        List<Department> departments = new ArrayList();
        String joinQuery = "SELECT deptId FROM department_news WHERE newsid = :newsid";

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

//    @Override
    public News findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }
}
