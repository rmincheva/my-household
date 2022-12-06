package com.wagawin.myhousehold.application.child;

import com.wagawin.myhousehold.application.person.PersonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "ChildInformation",
        description = "Represents information about a child containing the child`s parent and favorite meal.")
public record ChildInformationDto(@NotNull @Valid PersonDto parent, @Valid MealDto favoriteMeal) {
}
