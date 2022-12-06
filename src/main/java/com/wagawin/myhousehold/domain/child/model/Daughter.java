package com.wagawin.myhousehold.domain.child.model;

import com.wagawin.myhousehold.domain.person.model.Person;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Represents a daughter.
 */
public final class Daughter extends Child {

    /**
     * the hair color of the daughter
     */
    @Max(255)
    @NotBlank
    private final String hairColor;

    public Daughter(long id, String name, int age, String hairColor, Person parent, List<Meal> favoriteMeals) {
        super(id, name, age, Gender.FEMALE, parent, favoriteMeals);
        this.hairColor = hairColor;
    }

    public String hairColor() {
        return hairColor;
    }
}
