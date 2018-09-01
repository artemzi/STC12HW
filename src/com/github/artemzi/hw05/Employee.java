package com.github.artemzi.hw05;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Employee class required by home work assignment
 */
public class Employee implements Serializable {
    private String name;
    private int age;
    // salary value stored in cents
    private BigDecimal salary;
    private Jobs job;

    Employee(String name, int age, String salary, Jobs job) {
        this.name = name;
        this.age = age;
        this.salary = new BigDecimal(salary);
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public Jobs getJob() {
        return job;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                job == employee.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, job);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job=" + job +
                '}';
    }
}
