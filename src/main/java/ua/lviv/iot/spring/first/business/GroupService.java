package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.spring.first.dataaccess.GroupRepository;
import ua.lviv.iot.spring.first.rest.model.Group;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroup(Integer groupId) {
        return groupRepository.findById(groupId).get();
    }

    public boolean deleteById(Integer id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Group update(Integer id, Group newGroup, Group oldGroup) {
        if (groupRepository.existsById(id)) {
            BeanUtils.copyProperties(groupRepository.findById(id).get(), oldGroup);
            groupRepository.save(newGroup);
        }
        return oldGroup;
    }
}
