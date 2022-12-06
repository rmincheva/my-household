package com.wagawin.myhousehold.infrastructure.persistence.person.model;

import com.wagawin.myhousehold.infrastructure.persistence.child.model.ChildEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Set;

import static com.wagawin.myhousehold.infrastructure.persistence.person.model.PersonEntity.Persistence.TABLE_PERSON;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = TABLE_PERSON)
public class PersonEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_PERSON = "person";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_AGE = "age";
        private static final String COLUMN_HOUSE_ID = "house_id";

        private static final String MAPPED_PROPERTY_CHILD_PARENT = "parent";
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Persistence.COLUMN_HOUSE_ID, referencedColumnName = HouseEntity.Persistence.COLUMN_ID, foreignKey = @ForeignKey(name = "fk_person_house_id"), nullable = false)
    private HouseEntity house;

    @OneToMany(mappedBy = Persistence.MAPPED_PROPERTY_CHILD_PARENT, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ChildEntity> children;

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

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public Set<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEntity> children) {
        this.children = children;
    }
}
