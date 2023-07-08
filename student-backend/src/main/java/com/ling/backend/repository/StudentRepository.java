package com.ling.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ling.backend.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

}
