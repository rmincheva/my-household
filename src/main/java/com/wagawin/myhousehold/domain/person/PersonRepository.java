package com.wagawin.myhousehold.domain.person;

import com.wagawin.myhousehold.domain.person.model.ParentSummary;
import com.wagawin.myhousehold.domain.person.model.Person;

/**
 * Repository for persons
 */
public interface PersonRepository {

    /**
     * Gets a person for a given id
     *
     * @param id the id of the person
     * @return a person object
     */
    Person person(long id);

    /**
     * Gets a parent-children summary
     *
     * @return a parent summary object
     */
    ParentSummary parentSummary();
}
