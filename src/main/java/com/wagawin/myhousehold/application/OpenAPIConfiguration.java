package com.wagawin.myhousehold.application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(title = "My household application API", version = "1.0"),
        tags = {
                @Tag(name = "Persons", description = "RESTful resources responsible for persons"),
                @Tag(name = "Children", description = "RESTful resources responsible for children")
        })
public class OpenAPIConfiguration {

}
