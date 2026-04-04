package re.edu.coursemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.student.StudentResponse;
import re.edu.coursemanagement.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public PageResponse<StudentResponse> findAllStudent(int page, int size, String sortBy, Sort.Direction direction, String keyword) {
        Pageable pageable;
        if (sortBy != null && direction != null) {
            pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        }else
            pageable = PageRequest.of(page, size);


        String keywordParam = (keyword != null) ? "%" + keyword.toLowerCase() + "%" : null;
        Page<StudentResponse> pageStudent= studentRepository.findAllStudents(keywordParam, pageable);

        return mapPageToPageResponse(pageStudent);
    }

    @Override
    public StudentResponse findStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudentById(Long id) {

    }

    private PageResponse<StudentResponse> mapPageToPageResponse( Page<StudentResponse> page) {
        return  PageResponse.<StudentResponse>builder()
                .items(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }


}
