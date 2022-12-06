package com.wagawin.myhousehold.infrastructure.persistence.person;

import com.wagawin.myhousehold.domain.person.PersonRepository;
import com.wagawin.myhousehold.domain.person.model.House;
import com.wagawin.myhousehold.domain.person.model.ParentSummary;
import com.wagawin.myhousehold.domain.person.model.Person;
import com.wagawin.myhousehold.infrastructure.persistence.person.model.HouseEntity;
import com.wagawin.myhousehold.infrastructure.persistence.person.model.PersonEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A JPA implementation of PersonRepository
 */
@Repository
public class JpaPersonRepository implements PersonRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaPersonRepository.class);

    private static final String PARENT_SUMMARY_CACHE = "parentSummary";

    private static final String PARENT_SUMMARY_QUERY = """
            select COUNT(p.id) parentCount, childCount
            from person p
            left join (select parent_id , count(id) childCount
                      from child
                      group by parent_id) c on p.id = c.parent_id
            group by childCount
            """;

    @PersistenceContext
    private EntityManager em;

    /**
     * Gets a person for a given id
     *
     * @param id the id of the person
     * @return a person object
     */
    @Override
    public Person person(long id) {
        return Optional.ofNullable(em.find(PersonEntity.class, id))
                .map(this::asPerson)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Gets a summary containing the amount of persons to the amount of children
     *
     * @return a summary object
     */
    @Override
    @Cacheable(value = PARENT_SUMMARY_CACHE, sync = true)
    public ParentSummary parentSummary() {
        return getAndCacheParentSummary();
    }

    /**
     * Gets a summary containing the amount of persons to the amount of children and caches it
     *
     * @return a summary object
     */
    @CachePut(value = PARENT_SUMMARY_CACHE)
    public ParentSummary getAndCacheParentSummary() {
        Query query = em.createNativeQuery(PARENT_SUMMARY_QUERY);
        List<ParentSummary.Amount> summary = ((List<Object[]>) query.getResultList())
                .stream()
                .map(record -> {
                    Long parentCount = (Long) record[0];
                    Long childrenCount = Optional.ofNullable((Long) record[1]).orElse(0L);
                    return ParentSummary.Amount.of(parentCount.intValue(), childrenCount.intValue());
                })
                .toList();

        LOGGER.debug("Caching the result of parent summary as '{}'", PARENT_SUMMARY_CACHE);
        return new ParentSummary(summary);
    }

    private Person asPerson(PersonEntity person) {
        House house = asHouse(person.getHouse());
        return new Person(person.getId(), person.getName(), person.getAge(), house, Collections.emptyList());
    }

    private House asHouse(HouseEntity house) {
        return new House(house.id(), house.type(), house.address(), house.zipcode());
    }
}
