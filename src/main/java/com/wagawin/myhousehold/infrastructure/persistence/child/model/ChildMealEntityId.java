package com.wagawin.myhousehold.infrastructure.persistence.child.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ChildMealEntityId implements Serializable {

    public static final class Persistence {
        private static final String COLUMN_MEAL_ID = "meal_id";
        private static final String COLUMN_CHILD_ID = "child_id";
    }

    @Column(name = Persistence.COLUMN_CHILD_ID)
    private long childId;

    @Column(name = Persistence.COLUMN_MEAL_ID)
    private long mealId;

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }
}
