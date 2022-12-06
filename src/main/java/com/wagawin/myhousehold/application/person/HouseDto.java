package com.wagawin.myhousehold.application.person;

import com.wagawin.myhousehold.domain.person.model.HouseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "House",
        description = "Represents a house",
        example = """
                {
                	"id": "1",
                	"type": "FLAT",
                	"address": "Baker Str. 6, London",
                	"zipcode": "76188"
                }
                """)
public record HouseDto(@Schema(description = "The id of the house")
                       @Positive
                       long id,

                       @Schema(description = "The type of the house")
                       @NotNull
                       HouseType type,

                       @Schema(description = "The address of the house")
                       @NotBlank
                       String address,

                       @Schema(description = "The zipcode of the house")
                       @NotBlank
                       String zipcode) {
}
