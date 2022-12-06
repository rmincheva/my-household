package com.wagawin.myhousehold.infrastructure.persistence.child;

import com.wagawin.myhousehold.domain.child.*;
import com.wagawin.myhousehold.domain.child.model.*;
import com.wagawin.myhousehold.domain.person.model.*;
import com.wagawin.myhousehold.infrastructure.persistence.child.model.*;
import com.wagawin.myhousehold.infrastructure.persistence.person.model.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * A JPA implementation of ChildRepository
 */
@Repository
public class JpaChildRepository implements ChildRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Gets a child for a given id
     *
     * @param id the id of the child
     * @return a child object
     */
    public Child child(long id) {
        return Optional.ofNullable(em.find(ChildEntity.class, id))
                .map(this::asChild)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Child asChild(ChildEntity child) {
        Person parent = asPerson(child.getParent());
        List<Meal> favoriteMeals = asMeals(child.getFavoriteMeals());
        return switch (child.getGender()) {
            case FEMALE -> {
                final DaughterEntity daughter = (DaughterEntity) child;
                yield new Daughter(daughter.getId(), daughter.getName(), daughter.getAge(),
                        daughter.getHairColor(), parent, favoriteMeals);
            }
            case MALE -> {
                final SonEntity son = (SonEntity) child;
                yield new Son(son.getId(), son.getName(), son.getAge(), son.getBicycleColor(), parent,
                        favoriteMeals);
            }
        };
    }

    private Person asPerson(PersonEntity person) {
        return new Person(person.getId(), person.getName(), person.getAge());
    }

    private List<Meal> asMeals(Set<ChildMealEntity> meals) {
        return meals.stream()
                .map(ChildMealEntity::getMeal)
                .map(meal -> new Meal(meal.getId(), meal.getName(), meal.getInvented()))
                .toList();
    }
}
