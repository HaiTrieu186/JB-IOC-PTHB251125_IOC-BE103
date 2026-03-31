package re.edu.coursemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.Enrollment;
import re.edu.coursemanagement.model.Instructor;
import re.edu.coursemanagement.repository.IRepository;
import re.edu.coursemanagement.service.IEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private  final IEnrollmentService enrollmentService;

    public EnrollmentController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<?> getEnrollments() {
        List<Enrollment> list = enrollmentService.getAllEnrollment();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollment(@PathVariable int id) {
        Enrollment e = enrollmentService.getEnrollmentById(id);

        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment e = enrollmentService.createEnrollment(enrollment);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment) {
        Enrollment e = enrollmentService.updateEnrollment(enrollment, id);

        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable int id) {
        Enrollment e = enrollmentService.deleteEnrollmentById(id);

        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
