package com.github.artemzi.hw15.realExample;

import com.github.artemzi.hw15.realExample.connectionManager.FactoryDAO;
import com.github.artemzi.hw15.realExample.dao.DAO;
import com.github.artemzi.hw15.realExample.dao.StudentDaoImpl;
import com.github.artemzi.hw15.realExample.pojo.Student;

/**
 * Before running application create hw15.properties file (you can just rename hw15.properties.example)
 * and specify connection properties. Database must be presented (use hw15.sql file, or backup.sql)
 */
public class Main {
    public static void main(String[] args) {
        DAO studentDao = new StudentDaoImpl(FactoryDAO.getInstance("javabase.jdbc"));
        Student student = studentDao.getById(6);
        System.out.println(student);

        student.setName("Mikhail");
        student.setFamilyName("Doe");
        studentDao.update(student);

        student = studentDao.getById(6);
        System.out.println(student);

//        studentDao.deleteById(4);
    }
}
