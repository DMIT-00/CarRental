package com.dmit.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_model", uniqueConstraints = { @UniqueConstraint(name = "unique_model_name", columnNames = { "model_name", "brand_id" }) })
public class CarModel {
    @Id
    @Column(name = "model_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private CarBrand carBrand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarModel carModel = (CarModel) o;
        return id != null && Objects.equals(id, carModel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
