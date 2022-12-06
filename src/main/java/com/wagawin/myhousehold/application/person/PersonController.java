package com.wagawin.myhousehold.application.person;

import com.wagawin.myhousehold.domain.person.PersonService;
import com.wagawin.myhousehold.domain.person.model.House;
import com.wagawin.myhousehold.domain.person.model.ParentSummary;
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

import java.util.Comparator;

@Tag(name = "Persons")
@Validated
@RestController
@RequestMapping(PersonController.V1_PERSONS_URL)
public class PersonController {
    public static final String V1_PERSONS_URL = "/v1/persons";
    public static final String PERSON_HOUSE = "/{id}/house";
    public static final String PARENT_SUMMARY = "/children";

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Gets the house of a person.",
            description = "Returns details about the house where a given person lives.",
            operationId = "Persons.getHouse", responses = {
            @ApiResponse(responseCode = "200", description = "The house is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HouseDto.class))),
            @ApiResponse(responseCode = "404", description = "No person exists for the given id.",
                    content = @Content) })
    @GetMapping(PERSON_HOUSE)
    public HouseDto getHouse(@PathVariable @Positive @Schema(example = "1") long id) {
        Person person = personService.getPerson(id);
        return asHouseDto(person.house());
    }

    @Operation(summary = "Gets information about how many parents have n children.",
            description = "Returns a summary report showing how many parents are having n children.",
            operationId = "Persons.getParentSummary", responses = {
            @ApiResponse(responseCode = "200", description = "The summary report is returned.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParentSummaryDto.class))) })
    @GetMapping(PARENT_SUMMARY)
    public ParentSummaryDto getParentSummary() {
        ParentSummary summary = personService.getParentSummary();
        return asParentSummaryDto(summary);
    }

    private HouseDto asHouseDto(House house) {
        return new HouseDto(house.id(), house.type(), house.address(), house.zipcode());
    }

    private ParentSummaryDto asParentSummaryDto(ParentSummary summary) {
        Integer[] parentSummary = summary
                .amounts()
                .stream()
                .sorted(Comparator.comparing(ParentSummary.Amount::children))
                .map(ParentSummary.Amount::parent)
                .toArray(Integer[]::new);
        return new ParentSummaryDto(parentSummary);
    }
}
