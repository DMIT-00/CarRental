package com.dmit.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_brand")
public class CarBrand {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Long id;

    @Column(name = "brand_name", unique = true, nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CarModel> carModels = new ArrayList<>();

    public void addModel(CarModel carModel) {
        carModels.add(carModel);
        carModel.setCarBrand(this);
    }

    public void removeModel(CarModel carModel) {
        carModels.remove(carModel);
        carModel.setCarBrand(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarBrand carBrand = (CarBrand) o;
        return id != null && Objects.equals(id, carBrand.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
