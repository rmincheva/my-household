package com.wagawin.myhousehold.infrastructure.persistence.child.model;

import com.wagawin.myhousehold.domain.child.model.Gender;
import com.wagawin.myhousehold.infrastructure.persistence.person.model.PersonEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = ChildEntity.Persistence.TABLE_CHILD)
@DiscriminatorColumn(name = ChildEntity.Persistence.COLUMN_GENDER, discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class ChildEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_CHILD = "child";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        private static final String COLUMN_PARENT_ID = "parent_id";
        public static final String MAPPED_BY_COLUMN_CHILD = "child";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_ID)
    private long id;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_NAME, nullable = false)
    private String name;

    @Positive
    @Column(name = Persistence.COLUMN_AGE, nullable = false)
    private int age;

    @Column(name = Persistence.COLUMN_GENDER, nullable = false, insertable = false, updatable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Persistence.COLUMN_PARENT_ID, nullable = false, foreignKey = @ForeignKey(name = "fk_child_person_id"))
    private PersonEntity parent;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = Persistence.MAPPED_BY_COLUMN_CHILD, fetch = FetchType.LAZY)
    @OrderBy("priority asc")
    private Set<ChildMealEntity> favoriteMeals = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PersonEntity getParent() {
        return parent;
    }

    public void setParent(PersonEntity parent) {
        this.parent = parent;
    }

    public Set<ChildMealEntity> getFavoriteMeals() {
        return favoriteMeals;
    }

    public void setFavoriteMeals(Set<ChildMealEntity> favoriteMeals) {
        this.favoriteMeals = favoriteMeals;
    }
}
