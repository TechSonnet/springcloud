package com.techsonnet.repository;

import com.techsonnet.entity.Student;

import java.util.Collection;


public interface StudentRepository {
    Collection<Student> findAll();
    Student findById(Integer id);
    void saveOrUpdate(Student student);
    void deleteById(Integer id);
}
