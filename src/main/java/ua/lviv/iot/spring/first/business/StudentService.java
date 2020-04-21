package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.spring.first.dataaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Student getStudent(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    public boolean deleteById(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Student> getAllByName(String name) {

        return studentRepository.findAllByName(name);
    }
    
    public Student update(Integer id, Student newStudent, Student oldStudent) {
        if (studentRepository.existsById(id)) {
            BeanUtils.copyProperties(studentRepository.findById(id).get(), oldStudent);
            studentRepository.save(newStudent);
        }
        return oldStudent;
    }

}
