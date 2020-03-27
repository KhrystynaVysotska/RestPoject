package business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import databaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentReposetory;
	
	public Student createStudent(Student student) {
		
		return studentReposetory.save(student);
	
	}

}
