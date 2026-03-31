package re.edu.coursemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.model.Enrollment;
import re.edu.coursemanagement.repository.IRepository;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {
    private final IRepository<Enrollment> enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(IRepository<Enrollment> enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy đăng ký với id " + id));
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    @Override
    public Enrollment updateEnrollment(Enrollment enrollment, int id) {
        return enrollmentRepository.update(id, enrollment);
    }

    @Override
    public Enrollment deleteEnrollmentById(int id) {
        return enrollmentRepository.deleteById(id);
    }
}
