package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.Course;
import re.edu.coursemanagement.service.ICourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<?> getCourses(){
        List<Course> list= courseService.getAllCourse();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id){
        Course c= courseService.getCourseById(id);

        if (c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        Course c= courseService.createCourse(course);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse (@PathVariable int id, @RequestBody Course course){
        Course c= courseService.updateCourse(course, id);

        if (c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        Course c= courseService.deleteCourseById(id);

        if (c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
