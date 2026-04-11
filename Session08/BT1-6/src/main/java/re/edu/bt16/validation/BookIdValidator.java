package re.edu.bt16.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import re.edu.bt16.repository.IBookRepository;

@Component
@RequiredArgsConstructor //2 tham số ConstraintValidator<Annotation, Kieu_DuLieu_Can_KiemTra>
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    private final IBookRepository bookRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return bookRepository.existsById(value);
    }
}
