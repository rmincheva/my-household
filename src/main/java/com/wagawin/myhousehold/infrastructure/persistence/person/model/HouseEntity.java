package com.wagawin.myhousehold.infrastructure.persistence.person.model;

import com.wagawin.myhousehold.domain.person.model.HouseType;
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

import static com.wagawin.myhousehold.infrastructure.persistence.person.model.HouseEntity.Persistence.TABLE_HOUSE;

@Entity
@Table(name = TABLE_HOUSE)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HouseEntity implements Serializable {

    public static final class Persistence {
        public static final String TABLE_HOUSE = "house";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_TYPE = "type";
        private static final String COLUMN_ADDRESS = "address";
        private static final String COLUMN_ZIPCODE = "zipcode";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_ID)
    private long id;

    @NotNull
    @Column(name = Persistence.COLUMN_TYPE, nullable = false)
    private HouseType type;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_ADDRESS, nullable = false)
    private String address;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_ZIPCODE, nullable = false)
    private String zipcode;

    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HouseType type() {
        return type;
    }

    public void setType(HouseType type) {
        this.type = type;
    }

    public String address() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String zipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
