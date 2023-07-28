package com.exmaple.demoForJpa.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exmaple.demoForJpa.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
