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

import ua.lviv.iot.spring.first.business.GroupService;
import ua.lviv.iot.spring.first.rest.model.Group;

@RequestMapping("/groups")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Group getGroup(final @PathVariable("id") Integer groupId) {
        return groupService.getGroup(groupId);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public Group createGroup(final @RequestBody Group group) {
        return groupService.createGroup(group);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer groupId) {
        if (groupService.deleteById(groupId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Group> updateGroup(final @PathVariable("id") Integer groupId, @RequestBody Group group) {
        group.setId(groupId);
        Group updatedGroup = groupService.update(groupId, group, new Group());
        if (updatedGroup != null) {
            return new ResponseEntity<Group>(updatedGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
    }
}
