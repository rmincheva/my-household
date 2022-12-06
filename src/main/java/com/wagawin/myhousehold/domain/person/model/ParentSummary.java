package com.wagawin.myhousehold.domain.person.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

/**
 * Represents parent summary.
 */
public record ParentSummary(List<Amount> amounts) {

    /**
     * Represents the amount of parents having the given amount of children.
     */
    public record Amount(@Positive int parent, @PositiveOrZero int children) {

        public static Amount of(int parent, int children) {
            return new Amount(parent, children);
        }
    }
}
