package re.edu.coursemangement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemangement.entity.ApiResponse;
import re.edu.coursemangement.entity.Course;
import re.edu.coursemangement.entity.DTO.StudentEnrollmentCreateRequest;
import re.edu.coursemangement.entity.StudentEnrollment;
import re.edu.coursemangement.service.IStudentEnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class StudentEnrollmentController {
    private  final IStudentEnrollmentService enrollmentService;

    public ResponseEntity<?> enroll(@RequestBody StudentEnrollmentCreateRequest request){
        ApiResponse<StudentEnrollment> response= new ApiResponse<>();
        try {
            StudentEnrollment studentEnrollment= enrollmentService.enrollStudent(request.getStudentId(), request.getCourseId());
            response.setMessage("success");
            response.setSuccess(true);
            response.setData(studentEnrollment);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
