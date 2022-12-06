package com.wagawin.myhousehold;

import com.wagawin.myhousehold.domain.child.ChildRepository;
import com.wagawin.myhousehold.domain.child.ChildService;
import com.wagawin.myhousehold.domain.person.PersonRepository;
import com.wagawin.myhousehold.domain.person.PersonService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MyHouseholdTestConfiguration {

    @Bean
    public PersonService personService(PersonRepository repository) {
        return new PersonService(repository);
    }

    @Bean
    public ChildService childService(ChildRepository repository) {
        return new ChildService(repository);
    }
}
