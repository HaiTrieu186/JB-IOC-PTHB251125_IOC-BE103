package re.edu.coursemanagement.service;


import re.edu.coursemanagement.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> getAllEnrollment();
    Enrollment getEnrollmentById(int id);
    Enrollment createEnrollment(Enrollment enrollment);
    Enrollment updateEnrollment(Enrollment enrollment, int id);
    Enrollment deleteEnrollmentById(int id);
}
