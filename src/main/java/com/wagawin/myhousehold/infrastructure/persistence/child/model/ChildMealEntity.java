package com.wagawin.myhousehold.infrastructure.persistence.child.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Entity
@Table(name = ChildMealEntity.Persistence.TABLE_CHILD_MEAL)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChildMealEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_CHILD_MEAL = "child_meal";
        private static final String COLUMN_CHILD_ID = "child_id";
        private static final String COLUMN_MEAL_ID = "meal_id";
        private static final String COLUMN_PRIORITY = "priority";
    }

    @EmbeddedId
    private ChildMealEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Persistence.COLUMN_CHILD_ID, insertable = false, updatable = false)
    private ChildEntity child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Persistence.COLUMN_MEAL_ID, insertable = false, updatable = false)
    private MealEntity meal;

    @Column(name = Persistence.COLUMN_PRIORITY)
    private int priority;

    public ChildMealEntity() {
        this.id = new ChildMealEntityId();
    }

    public ChildMealEntityId getId() {
        return id;
    }

    public void setId(ChildMealEntityId id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ChildEntity getChild() {
        return child;
    }

    public void setChild(ChildEntity child) {
        this.child = child;
    }

    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }
}
