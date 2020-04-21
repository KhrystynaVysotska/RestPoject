package ua.lviv.iot.spring.first.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

@RequestMapping("/students")
@RestController
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents(final @RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return studentService.findAll();
        }
        return studentService.getAllByName(name);
    }

    @GetMapping(path = "/{id}")
    public Student getStudent(final @PathVariable("id") Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Student createStudent(final @RequestBody Student student) {
        return studentService.createStudent(student);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
        if (studentService.deleteById(studentId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(final @PathVariable("id") Integer studentId,
            @RequestBody Student student) {
        student.setId(studentId);
        Student updatedStudent = studentService.update(studentId, student, new Student());
        if (updatedStudent != null) {
            return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }
}
