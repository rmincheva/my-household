package com.wagawin.myhousehold.domain.child;

import com.wagawin.myhousehold.domain.child.model.Child;

/**
 * Repository for children
 */
public interface ChildRepository {

    /**
     * Gets a child for a given id
     *
     * @param id the id of the child
     * @return a child object
     */
    Child child(long id);
}
