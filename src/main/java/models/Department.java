package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department {


    private int id;
    private String title;
    private List<Employee> employees;

    public Department() {}

    public Department(String title) {
        this.title = title;

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "department")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}