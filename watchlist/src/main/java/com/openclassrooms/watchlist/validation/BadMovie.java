package com.openclassrooms.watchlist.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {

    String message()
            default " if a movie is less than 6 The comment field should have at least 15 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
