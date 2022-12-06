package com.wagawin.myhousehold.domain.child;

import com.wagawin.myhousehold.domain.child.model.Child;

public class ChildService {

    private final ChildRepository repository;

    public ChildService(ChildRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets a child for a given id
     *
     * @param id the id of the child
     * @return a child object
     */
    public Child getChild(long id) {
        return repository.child(id);
    }
}
