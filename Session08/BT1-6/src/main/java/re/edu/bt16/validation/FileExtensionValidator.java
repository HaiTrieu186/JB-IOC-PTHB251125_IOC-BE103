package re.edu.bt16.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {
    private String[] allowedExtensions;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
       this.allowedExtensions = constraintAnnotation.extensions();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) return true;

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) return true;

        for (String extension : this.allowedExtensions) {
            if (originalFilename.toLowerCase().endsWith("."+extension.toLowerCase())) {
                return true;
            }
        }

        return false;
    }


}
