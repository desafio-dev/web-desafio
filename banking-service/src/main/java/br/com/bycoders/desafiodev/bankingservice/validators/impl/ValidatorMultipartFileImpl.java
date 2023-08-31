package br.com.bycoders.desafiodev.bankingservice.validators.impl;


import br.com.bycoders.desafiodev.bankingservice.validators.ValidMultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

public class ValidatorMultipartFileImpl implements ConstraintValidator<ValidMultipartFile, MultipartFile> {
    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new MultipartException("The CNAB file are mandatory.");
        }

        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null || !fileName.endsWith(".txt")) {
            throw new MultipartException("Invalid file type. Only '.txt' files are allowed.");
        }

        return true;
    }
}
