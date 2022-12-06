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
@Table(name = SonEntity.Persistence.TABLE_SON)
@DiscriminatorValue(SonEntity.Persistence.DISCRIMINATOR_MALE)
@PrimaryKeyJoinColumn(name = SonEntity.Persistence.COLUMN_ID, foreignKey = @ForeignKey(name = "fk_son_child_id"))
public final class SonEntity extends ChildEntity {

    public static final class Persistence {
        public static final String TABLE_SON = "son";
        public static final String COLUMN_ID = "id";
        private static final String COLUMN_BICYCLE_COLOR = "bicycleColor";
        public static final String DISCRIMINATOR_MALE = "0";
    }

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_BICYCLE_COLOR, nullable = false)
    private String bicycleColor;

    public String getBicycleColor() {
        return bicycleColor;
    }

    public void setBicycleColor(String bicycleColor) {
        this.bicycleColor = bicycleColor;
    }
}
