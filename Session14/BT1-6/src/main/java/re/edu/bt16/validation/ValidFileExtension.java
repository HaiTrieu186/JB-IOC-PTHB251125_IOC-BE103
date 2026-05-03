package re.edu.bt16.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileExtensionValidator.class)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFileExtension {
    String message() default "Lỗi: Định dạng file không hợp lệ";
    String[] extensions() default {}; //ds đuôi cho phép
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
