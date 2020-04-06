package ua.lviv.iot.spring.first.dataaccess;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.spring.first.rest.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findAllByName(String name);
	
	List<Student> findAllByNameAndLastName(String name, String lastName);
	
	String query = "";
}

