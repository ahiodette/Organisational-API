

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Department {
    private String depname;
    private String depdescr;
    private int depsize;
    private int id;

    public Department(String depname, String depdescr, int depsize) {
        this.depname = depname;
        this.depdescr = depdescr;
        this.depsize = depsize;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getDepdescr() {
        return depdescr;
    }

    public void setDepdescr(String depdescr) {
        this.depdescr = depdescr;
    }

    public int getDepsize() {
        return depsize;
    }

    public void setDepsize(int depsize) {
        this.depsize = depsize;
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
        Department that = (Department) o;
        return depsize == that.depsize &&
                id == that.id &&
                Objects.equals(depname, that.depname) &&
                Objects.equals(depdescr, that.depdescr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depname, depdescr, depsize, id);
    }

    public void save(Department department){
        String sql = "INSERT INTO departments(depname,depdescr,depsize) VALUES (:depname, :depdescr, :depsize)";
        try (Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
//            department.setDepid(id);
        } catch (Sql2oException ex){

            System.out.println(ex);
        }
    }


    public static List<Department> getAll() {
        String sql = "SELECT * FROM departments";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Department.class);
        }
    }

}
