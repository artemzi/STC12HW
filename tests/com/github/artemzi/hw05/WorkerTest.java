package com.github.artemzi.hw05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class WorkerTest {
    private static final String FILE_NAME = "data/TEST_DATABASE";

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker(FILE_NAME);
    }

    @AfterAll
    public static void cleanUp() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }

    @Test
    public void employeeCanBeSaved() {
        Employee employee = new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER);
        boolean saved = worker.save(employee);
        Assertions.assertTrue(saved);
    }

    @Test
    public void getByNameMustReturnEmployee() {
        Employee employee = worker.getByName("Mike Doe");
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employee.getName(), "Mike Doe");
    }

    @Test
    public void getByJobMustReturnList() {
        List<Employee> employee = worker.getByJob(Jobs.DEVELOPER);
        Assertions.assertNotNull(employee);
        for (Employee e : employee) {
            Assertions.assertEquals(e.getJob(), Jobs.DEVELOPER);
        }
    }

    @Test
    public void deleteMustRemoveEmployee() {
        Employee employee = new Employee("Mike Doe", 25, "100000", Jobs.DEVELOPER);
        // employee presented in file before cleanUp() is called
        // bur method called for case if test methods call order will changed
        worker.save(employee);
        Assertions.assertTrue(worker.delete(employee));
    }

    @Test
    public void saveOrUpdateMustSaveNewEmployee() {
        Employee employeeForUpdate = new Employee("Mike Doe", 45, "100", Jobs.MANAGER);
        Assertions.assertTrue(worker.save(employeeForUpdate));
    }

    @Test
    public void saveOrUpdateMustUpdateEmployeeWithNameWhichAlreadyExists() {
        Employee employeeForUpdate = new Employee("Mike Doe", 25, "100", Jobs.CTO);
        worker.saveOrUpdate(employeeForUpdate);

        employeeForUpdate = worker.getByName("Mike Doe");
        Assertions.assertEquals(employeeForUpdate.getJob(), Jobs.CTO);
        Assertions.assertEquals(employeeForUpdate.getAge(), 25);
    }

    @Test
    public void changeAllWorkMustChangeJobs() {
        Employee employee1 = new Employee("Mike 1", 25, "100000", Jobs.DEVELOPER);
        Employee employee2 = new Employee("John 1", 35, "100000", Jobs.DEVELOPER);
        worker.save(employee1);
        worker.save(employee2);

        Assertions.assertTrue(worker.changeAllWork(Jobs.DEVELOPER, Jobs.CTO));
        employee1 = worker.getByName("Mike 1");
        employee2 = worker.getByName("John 1");
        Assertions.assertEquals(employee1.getJob(), Jobs.CTO);
        Assertions.assertEquals(employee2.getJob(), Jobs.CTO);
    }
}
