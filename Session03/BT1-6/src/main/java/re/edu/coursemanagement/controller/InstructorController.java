package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.Instructor;
import re.edu.coursemanagement.service.IInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<?> getInstructors() {
        List<Instructor> list = instructorService.getAllInstructor();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable int id) {
        Instructor i = instructorService.getInstructorById(id);

        if (i == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        Instructor i = instructorService.createInstructor(instructor);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        Instructor i = instructorService.updateInstructor(instructor, id);

        if (i == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        Instructor i = instructorService.deleteInstructorById(id);

        if (i == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
