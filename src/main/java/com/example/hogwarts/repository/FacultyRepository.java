package com.example.hogwarts.repository;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByColor(String color);

    List<Faculty> findAllByColorLikeIgnoreCaseOrNameLikeIgnoreCase(String color, String name);

    List<Faculty> findAllByStudents(List<Student> students);
}
