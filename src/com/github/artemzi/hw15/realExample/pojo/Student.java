package com.github.artemzi.hw15.realExample.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private int id;
    private String name;
    private String familyName;
    private int age;
    private String contact;
    private int city;

    public Student(int id, String name, String familyName, int age, String contact, int city) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.age = age;
        this.contact = contact;
        this.city = city;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                city == student.city &&
                Objects.equals(name, student.name) &&
                Objects.equals(familyName, student.familyName) &&
                Objects.equals(contact, student.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, familyName, age, contact, city);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", city=" + city +
                '}';
    }
}
