package com.wagawin.myhousehold.application.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Schema(name = "Person",
        description = "Represents a person",
        example = """
                {
                	"id": "1",
                	"name": "John Doe",
                	"age": "45",
                }
                """)
public record PersonDto(@Schema(description = "The id of the person")
                        @Positive
                        long id,

                        @Schema(description = "The name of the person")
                        @NotBlank
                        String name,

                        @Schema(description = "The age of the person")
                        @Positive
                        int age) {
}
