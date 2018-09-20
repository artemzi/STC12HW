package com.github.artemzi.hw15.realExample;

import com.github.artemzi.hw15.realExample.dao.StudentDao;
import com.github.artemzi.hw15.realExample.dao.StudentDaoImpl;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
/*        Student student = studentDao.getStudentById(5);
        System.out.println(student);

        student.setName("Mikhail");
        student.setFamilyName("Nekhoroshev");
        studentDao.update(student);*/

        studentDao.deleteStudentById(12);
    }
}
