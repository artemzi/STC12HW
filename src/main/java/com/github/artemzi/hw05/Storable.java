package com.github.artemzi.hw05;

import java.util.List;

/**
 * 1.      Реализовать следующие методы:
 *
 * a.      boolean save (Employee), дописывающий сотрудника в конец файла
 * b.      boolean delete (Employee), удаляющий сотрудника из файла
 * c.      Employee getByName (тип name), возвращающий сотрудника по полному совпадению имени
 * d.      List<Employee> getByJob(тип job), возвращающий список сотрудников по должности
 * e.      boolean saveOrUpdate (Employee), выполняющий обновление, либо сохранение сотрудника в зависимости от того, есть ли он уже в файле
 * f.       boolean changeAllWork (какую должноcть, на какую должность), выполняющий замену заданной должности на заданную для всех сотрудников
 * g.      Используем сериализацию/десериализацию «Из коробки»
 */
public interface Storable {
    boolean save(Employee employee);
    boolean delete(Employee employee);
    Employee getByName(String name);
    List<Employee> getByJob(Jobs job);
    boolean saveOrUpdate(Employee employee);
    boolean changeAllWork(Jobs fromJobTitle, Jobs toJobTitle);
}
