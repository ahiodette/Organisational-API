package models;

import java.util.Objects;

public class Department {
    private String depName;
    private String depDescr;
    private int size;
    private int depId;

    public Department(String depName, String depDescr, int size) {
        this.depName = depName;
        this.depDescr = depDescr;
        this.size = size;
    }

    public String getDepName() {
        return depName;
    }

    public String getDepDescr() {
        return depDescr;
    }

    public int getSize() {
        return size;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public void setDepDescr(String depDescr) {
        this.depDescr = depDescr;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return size == that.size &&
                depId == that.depId &&
                Objects.equals(depName, that.depName) &&
                Objects.equals(depDescr, that.depDescr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depName, depDescr, size, depId);
    }
}
