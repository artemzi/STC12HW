package com.github.artemzi.hw15.realExample;

import com.github.artemzi.hw15.realExample.dao.CityDAO;
import com.github.artemzi.hw15.realExample.pojo.City;
import com.github.artemzi.hw15.realExample.services.FactoryDAO;
import com.github.artemzi.hw15.realExample.dao.DAO;
import com.github.artemzi.hw15.realExample.dao.StudentDAO;
import com.github.artemzi.hw15.realExample.pojo.Student;

/**
 * Before running application create hw15.properties file (you can just rename hw15.properties.example)
 * and specify connection properties. Database must be presented (use hw15.sql file, or backup.sql)
 */
public class Main {
    public static void main(String[] args) {
        StudentDAO studentDao = new StudentDAO(FactoryDAO.getInstance("javabase.jdbc"));
        Student student = studentDao.getById(6);
        System.out.println(student);

        student.setName("Mikhail");
        student.setFamilyName("Doe");
        studentDao.update(student);

        student = studentDao.getById(6);
        System.out.println(student);
        System.out.println(student.getCityObject()); // get connected city

        System.out.println("========");

        CityDAO cityDAO = new CityDAO(FactoryDAO.getInstance("javabase.jdbc"));
        City city = cityDAO.getById(1);
        System.out.println(city);

        System.out.println(city.getStudentsListInCurrentCity()); // get all connected students
//        city.setName("Abrva");
//        city.setCitizens(1000000000);
//        cityDAO.update(city);
//
//        city = cityDAO.getById(1);
//        System.out.println(city);
    }
}
