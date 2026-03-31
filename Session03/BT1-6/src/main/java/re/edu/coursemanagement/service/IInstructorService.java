package re.edu.coursemanagement.service;

import re.edu.coursemanagement.model.Instructor;

import java.util.List;

public interface IInstructorService {
    List<Instructor> getAllInstructor();
    Instructor getInstructorById(int id);
    Instructor createInstructor(Instructor  instructor);
    Instructor updateInstructor(Instructor instructor, int id);
    Instructor deleteInstructorById(int id);
}
