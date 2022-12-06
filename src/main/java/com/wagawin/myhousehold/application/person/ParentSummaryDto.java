package com.wagawin.myhousehold.application.person;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ParentSummary",
        description = """
                Represents parent summary as an array. Each array index is equal to the children count and each array
                element is equal to the parent count.""",
        example = """
                {
                   "parentSummary": [4, 7, 4, 4, 2]
                }
                """)
public record ParentSummaryDto(
        @ArraySchema(schema = @Schema(implementation = Integer.class)) Integer[] parentSummary) {
}
