package com.wagawin.myhousehold.domain.child.model;

import com.wagawin.myhousehold.domain.person.model.Person;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Represents a son.
 */
public final class Son extends Child {

    /**
     * the bicycle color of the son
     */
    @Max(255)
    @NotBlank
    private final String bicycleColor;

    public Son(long id, String name, int age, String bicycleColor, Person parent, List<Meal> favoriteMeals) {
        super(id, name, age, Gender.MALE, parent, favoriteMeals);
        this.bicycleColor = bicycleColor;
    }

    public String bicycleColor() {
        return bicycleColor;
    }
}
