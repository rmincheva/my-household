package com.wagawin.myhousehold.infrastructure.persistence.child.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = DaughterEntity.Persistence.TABLE_DAUGHTER)
@DiscriminatorValue(DaughterEntity.Persistence.DISCRIMINATOR_FEMALE)
@PrimaryKeyJoinColumn(name = DaughterEntity.Persistence.COLUMN_ID, foreignKey = @ForeignKey(name = "fk_daughter_child_id"))
public final class DaughterEntity extends ChildEntity {

    public static final class Persistence {
        public static final String TABLE_DAUGHTER = "daughter";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_HAIR_COLOR = "hairColor";
        public static final String DISCRIMINATOR_FEMALE = "1";
    }

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_HAIR_COLOR, nullable = false)
    private String hairColor;

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
}
