package com.wagawin.myhousehold.infrastructure.persistence.child.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDate;

import static com.wagawin.myhousehold.infrastructure.persistence.child.model.MealEntity.Persistence.TABLE_MEAL;

@Entity
@Table(name = TABLE_MEAL)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MealEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_MEAL = "meal";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_INVENTED = "invented";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_ID)
    private long id;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_NAME, nullable = false)
    private String name;

    @NotNull
    @Column(name = Persistence.COLUMN_INVENTED, nullable = false)
    private LocalDate invented;

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

    public LocalDate getInvented() {
        return invented;
    }

    public void setInvented(LocalDate invented) {
        this.invented = invented;
    }
}
