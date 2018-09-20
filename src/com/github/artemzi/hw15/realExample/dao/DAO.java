package com.github.artemzi.hw15.realExample.dao;

import com.github.artemzi.hw15.realExample.pojo.Student;

public interface DAO {
    public boolean add(Student student);

    public Student getById(int id);

    public boolean update(Student student);

    public boolean deleteById(int id);

    public boolean deleteByName(Student student);
}
