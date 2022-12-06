package com.wagawin.myhousehold.application.child;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Schema(name = "Meal",
        description = "Represents a meal",
        example = """
                {
                	"id": "1",
                	"name": "Pizza",
                	"invented": "2020-10-11",
                }
                """)
public record MealDto(@Schema(description = "The id of the meal")
                      @Positive
                      long id,

                      @Schema(description = "The name of the meal")
                      @NotBlank
                      String name,

                      @Schema(description = "The invention date of the meal")
                      @NotNull
                      LocalDate invented) {
}
