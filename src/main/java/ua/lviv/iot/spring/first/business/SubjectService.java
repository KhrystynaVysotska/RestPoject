package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.SubjectRepository;
import ua.lviv.iot.spring.first.rest.model.Subject;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepositiry;
	
	public List<Subject> findAll() {
		return subjectRepositiry.findAll();
	}
	
	public Subject createSubject(Subject subject) {
        return subjectRepositiry.save(subject);
    }

    public Subject getSubject(Integer subjectId) {
        return subjectRepositiry.findById(subjectId).get();
    }

    public boolean deleteById(Integer id) {
        if (subjectRepositiry.existsById(id)) {
            subjectRepositiry.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Subject update(Integer id, Subject newSubject, Subject oldSubject) {
        if (subjectRepositiry.existsById(id)) {
            BeanUtils.copyProperties(subjectRepositiry.findById(id).get(), oldSubject);
            subjectRepositiry.save(newSubject);
        }
        return oldSubject;
    }
}
