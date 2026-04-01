package re.edu.coursemangement.service;

import re.edu.coursemangement.entity.StudentEnrollment;

public interface IStudentEnrollmentService {
   StudentEnrollment enrollStudent(Long studentId, Long courseId);
}
