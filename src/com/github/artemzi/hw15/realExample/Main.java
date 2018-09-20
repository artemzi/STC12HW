package com.github.artemzi.hw15.realExample;

import com.github.artemzi.hw15.realExample.dao.FactoryDAO;
import com.github.artemzi.hw15.realExample.dao.StudentDao;
import com.github.artemzi.hw15.realExample.dao.StudentDaoImpl;
import com.github.artemzi.hw15.realExample.pojo.Student;

/**
 * Before running application create hw15.properties file (you can just rename hw15.properties.example)
 * and specify connection properties. Database must be presented (use hw15.sql file, or backup.sql)
 */
public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl(FactoryDAO.getInstance("javabase.jdbc"));
        Student student = studentDao.getStudentById(1);
        System.out.println(student);

//        student.setName("Mikhail");
//        student.setFamilyName("Nekhoroshev");
//        studentDao.update(student);

//        studentDao.deleteStudentById(12);
    }
}
