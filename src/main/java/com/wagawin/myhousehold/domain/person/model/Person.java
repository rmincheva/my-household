package com.wagawin.myhousehold.domain.person.model;

import com.wagawin.myhousehold.domain.child.model.Child;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Collections;
import java.util.List;

/**
 * Represents a person.
 */
public class Person {

    /**
     * the id of the person
     */
    @NotNull
    private final long id;

    /**
     * the name of the person
     */
    @Max(255)
    @NotBlank
    private final String name;

    /**
     * the age of the person
     */
    @Positive
    private final int age;

    /**
     * the house of the person
     */
    @NotNull
    @Valid
    private final House house;

    /**
     * the children of the person
     */
    @NotNull
    private final List<@Valid Child> children;

    public Person(long id, String name, int age) {
        this(id, name, age, null, Collections.emptyList());
    }

    public Person(long id, String name, int age, House house, List<Child> children) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.house = house;
        this.children = children;
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

    public House house() {
        return house;
    }

    public List<Child> children() {
        return children;
    }
}
