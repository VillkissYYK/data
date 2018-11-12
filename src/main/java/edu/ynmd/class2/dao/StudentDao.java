package edu.ynmd.class2.dao;

import edu.ynmd.class2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,String> {
}
