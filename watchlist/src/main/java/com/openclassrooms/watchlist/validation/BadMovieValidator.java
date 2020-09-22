package com.openclassrooms.watchlist.validation;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.validation.BadMovie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BadMovieValidator implements
        ConstraintValidator<BadMovie, WatchlistItem> {
    @Override
    public void initialize(BadMovie constraintAnnotation) {

    }

    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
        return false;
    }
}
