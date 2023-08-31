package br.com.bycoders.desafiodev.bankingservice.validators;

import br.com.bycoders.desafiodev.bankingservice.validators.impl.ValidatorMultipartFileImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorMultipartFileImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMultipartFile {

    String message() default "Invalid file type. Only '.txt' files are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
