package com.wagawin.myhousehold.domain.child.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Represents a meal.
 */
public class Meal {

    /**
     * the id of the meal
     */
    @NotNull
    private final long id;

    /**
     * the name of the meal
     */
    @Max(255)
    @NotBlank
    private final String name;

    /**
     * the invention date of the meal
     */
    @NotNull
    private final LocalDate invented;

    public Meal(long id, String name, LocalDate invented) {
        this.id = id;
        this.name = name;
        this.invented = invented;
    }

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public LocalDate invented() {
        return invented;
    }
}
