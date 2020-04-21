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
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.spring.first.business.SubjectService;
import ua.lviv.iot.spring.first.rest.model.Subject;

@RequestMapping("/subjects")
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Subject getSubject(final @PathVariable("id") Integer subjectId) {
        return subjectService.getSubject(subjectId);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Subject createSubject(final @RequestBody Subject subject) {
        return subjectService.createSubject(subject);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Integer subjectId) {
        if (subjectService.deleteById(subjectId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Subject> updateSubject(final @PathVariable("id") Integer subjectId,
            @RequestBody Subject subject) {
        subject.setId(subjectId);
        Subject updatedSubject = subjectService.update(subjectId, subject, new Subject());
        if (updatedSubject != null) {
            return new ResponseEntity<Subject>(updatedSubject, HttpStatus.OK);
        } else {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
    }

}
