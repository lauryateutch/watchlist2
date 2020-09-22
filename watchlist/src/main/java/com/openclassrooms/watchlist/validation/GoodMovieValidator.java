package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements
        ConstraintValidator <GoodMovie, WatchlistItem> {
    @Override
    public void initialize(GoodMovie constraintAnnotation) {

    }

    @Override
    public boolean isValid(WatchlistItem value,
                           ConstraintValidatorContext context) {

                               return !
                (Double.valueOf(value.getRating()) >= 8 &&  "L".equals(value.priority));
    }
    }

