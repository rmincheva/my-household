package com.wagawin.myhousehold.domain.person;

import com.wagawin.myhousehold.domain.person.model.ParentSummary;
import com.wagawin.myhousehold.domain.person.model.Person;

public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets a person for a given id
     *
     * @param id the id of the person
     * @return a person object
     */
    public Person getPerson(long id) {
        return repository.person(id);
    }

    /**
     * Gets a parent-children summary
     *
     * @return a parent summary object
     */
    public ParentSummary getParentSummary() {
        return repository.parentSummary();
    }
}
