package com.wagawin.myhousehold.domain.child.model;

import com.wagawin.myhousehold.domain.person.model.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

/**
 * Represents a child.
 */
public abstract sealed class Child permits Daughter, Son {

    /**
     * the id of the child
     */
    @NotNull
    private final long id;

    /**
     * the name of the child
     */
    @Max(255)
    @NotBlank
    private final String name;

    /**
     * the age of the child
     */
    @Positive
    private final int age;

    /**
     * the gender of the child
     */
    @NotNull
    private final Gender gender;

    /**
     * the parent of the child
     */
    @Valid
    @NotNull
    private final Person parent;

    /**
     * the favorite meals of the child
     */
    @NotNull
    private final List<Meal> favoriteMeals;

    protected Child(long id, String name, int age, Gender gender, Person parent, List<Meal> favoriteMeals) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.parent = parent;
        this.favoriteMeals = favoriteMeals;
    }

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public Gender gender() {
        return gender;
    }

    public Person parent() {
        return parent;
    }

    public List<Meal> favoriteMeals() {
        return favoriteMeals;
    }
}
