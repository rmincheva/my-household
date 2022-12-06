package com.wagawin.myhousehold.application.child;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Color",
        description = """
                Represents a color. In case it is related to a daughter her hair color is returned. In case it is related 
                to a son his bicycle color is returned.
                """,
        example = """
                {
                 	"hairColor": "blond"
                 }
                  """)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ColorDto(@Schema(description = "The hair color of the daughter")
                       String hairColor,

                       @Schema(description = "The bicycle color of the son")
                       String bicycleColor) {
    public static ColorDto forBicycle(String bicycleColor) {
        return new ColorDto(null, bicycleColor);
    }

    public static ColorDto forHair(String hairColor) {
        return new ColorDto(hairColor, null);
    }
}
