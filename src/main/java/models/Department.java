package models;

import java.util.Objects;

public class Department {
    private String depName;
    private String depDescr;
    private int depSize;
    private int id;

    public Department(String depName, String depDescr, int size) {
        this.depName = depName;
        this.depDescr = depDescr;
        this.depSize = size;
    }

    public String getDepName() {
        return depName;
    }

    public String getDepDescr() {
        return depDescr;
    }

    public int getDepSize() {
        return depSize;
    }

    public int getDepId() {
        return id;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public void setDepDescr(String depDescr) {
        this.depDescr = depDescr;
    }

    public void setDepSize(int depSize) {
        this.depSize = depSize;
    }

    public void setDepId(int depId) {
        this.id = depId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return depSize == that.depSize &&
                id == that.id &&
                Objects.equals(depName, that.depName) &&
                Objects.equals(depDescr, that.depDescr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depName, depDescr, depSize, id);
    }
}
