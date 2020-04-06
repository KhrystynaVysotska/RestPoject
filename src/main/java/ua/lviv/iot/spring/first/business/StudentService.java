package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.spring.first.dataaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentReposetory;

	public Student createStudent(Student student) {

		return studentReposetory.save(student);

	}

	public List<Student> findAll() {

		return studentReposetory.findAll();
	}

	public List<Student> getAllByName(String name) {

		return studentReposetory.findAllByName(name);
	}

}
