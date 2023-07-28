package com.exmaple.demoForJpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exmaple.demoForJpa.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
