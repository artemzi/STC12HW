package com.github.artemzi.hw05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class EmployeeTest {

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER);
    }

    @Test
    public void objectCanBeCreated() {
        Assertions.assertNotNull(employee);
    }

    @Test
    public void allFieldsCorrectlyCreated() {
        Assertions.assertEquals(employee.getName(), "Mike Doe");
        Assertions.assertEquals(employee.getJob(), Jobs.DEVELOPER);
        Assertions.assertEquals(employee.getAge(), 25);
        Assertions.assertEquals(employee.getSalary(), new BigDecimal("100000"));
    }

    @Test
    public void abjectsAreEquals() {
        Assertions.assertTrue(employee.equals(new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER)));
    }

    @Test
    public void hashCodeSameForEqualObjects() {
        Assertions.assertTrue(employee.hashCode() == new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER).hashCode());
    }

    @Test
    public void hashCodeDifferentForDifferentObjects() {
        Assertions.assertFalse(employee.hashCode() == new Employee("Uasya Pypkin", 25, "100000", Jobs.DEVELOPER).hashCode());
    }
}
