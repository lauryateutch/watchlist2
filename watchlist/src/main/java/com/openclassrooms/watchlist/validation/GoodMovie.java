package com.openclassrooms.watchlist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GoodMovieValidator.class)
public @interface GoodMovie {

    String message( )
            default "If a movie is as 8 then priority should be at least M";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
