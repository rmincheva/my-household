package com.wagawin.myhousehold.domain.person.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents a house.
 */
public class House {

    /**
     * the id of the house
     */
    @Positive
    private final long id;

    /**
     * the type of the house
     */
    @NotNull
    private final HouseType type;

    /**
     * the address of the house
     */
    @Max(255)
    @NotBlank
    private final String address;

    /**
     * the zip code of the house
     */
    @Max(255)
    @NotBlank
    private final String zipcode;

    public House(long id, HouseType type, String address, String zipcode) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.zipcode = zipcode;
    }

    public long id() {
        return id;
    }

    public HouseType type() {
        return type;
    }

    public String address() {
        return address;
    }

    public String zipcode() {
        return zipcode;
    }
}
