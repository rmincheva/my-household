package com.wagawin.myhousehold.application.child;

import com.wagawin.myhousehold.application.person.PersonDto;
import com.wagawin.myhousehold.domain.child.ChildService;
import com.wagawin.myhousehold.domain.child.model.Child;
import com.wagawin.myhousehold.domain.child.model.Daughter;
import com.wagawin.myhousehold.domain.child.model.Meal;
import com.wagawin.myhousehold.domain.child.model.Son;
import com.wagawin.myhousehold.domain.person.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Children")
@Validated
@RestController
@RequestMapping(ChildController.V1_CHILDREN_URL)
public class ChildController {
    public static final String V1_CHILDREN_URL = "/v1/children";
    public static final String CHILD_INFO = "/{id}/info";
    public static final String CHILD_COLOR = "/{id}/color";

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @Operation(summary = "Gets information about a child.",
            description = "Returns information details about a child with a given id.",
            operationId = "Children.getInformation", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChildInformationDto.class))),
            @ApiResponse(responseCode = "404", description = "No child exists for the given id.",
                    content = @Content) })
    @GetMapping(CHILD_INFO)
    public ChildInformationDto getInformation(@PathVariable @Positive @Schema(example = "1") long id) {
        Child child = childService.getChild(id);
        return asInformationDto(child);
    }

    @Operation(summary = "Gets information about a child`s color.",
            description = """
                    Returns the hair color of a child in case of a daughter and the bicycle color of a child in
                    case of a son.
                    """,
            operationId = "Persons.getHouse", responses = {
            @ApiResponse(responseCode = "200", description = "The color information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ColorDto.class))),
            @ApiResponse(responseCode = "404", description = "No child exists for the given id.",
                    content = @Content) })
    @GetMapping(CHILD_COLOR)
    public ColorDto getColor(@PathVariable @Positive @Schema(example = "1") long id) {
        Child child = childService.getChild(id);
        return asColorDto(child);
    }

    private ColorDto asColorDto(Child child) {
        if (child instanceof Daughter daughter) {
            return ColorDto.forHair(daughter.hairColor());
        }
        else if (child instanceof Son son) {
            return ColorDto.forBicycle(son.bicycleColor());
        }
        throw new IllegalArgumentException();
    }

    private ChildInformationDto asInformationDto(Child child) {
        PersonDto parent = asPersonDto(child.parent());

        MealDto favoriteMeal = !child.favoriteMeals().isEmpty() ?
                asMealDto(child.favoriteMeals().iterator().next()) :
                null;
        return new ChildInformationDto(parent, favoriteMeal);
    }

    private PersonDto asPersonDto(Person parent) {
        return new PersonDto(parent.id(), parent.name(), parent.age());
    }

    private MealDto asMealDto(Meal meal) {
        return new MealDto(meal.id(), meal.name(), meal.invented());
    }
}
