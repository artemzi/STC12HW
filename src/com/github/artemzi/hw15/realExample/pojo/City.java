package com.github.artemzi.hw15.realExample.pojo;

import com.github.artemzi.hw15.realExample.dao.StudentDAO;
import com.github.artemzi.hw15.realExample.services.FactoryDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class City implements Serializable {
    private int id;
    private String name;
    private int citizens;

    public City(int id, String name, int citizens) {
        this.id = id;
        this.name = name;
        this.citizens = citizens;
    }

    public List<Student> getStudentsListInCurrentCity() {
        StudentDAO studentDao = new StudentDAO(FactoryDAO.getInstance("javabase.jdbc"));

        return studentDao.listByCity(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                citizens == city.citizens &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, citizens);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", citizens=" + citizens +
                '}';
    }
}
