package com.dmit.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private List<CarModel> carModels;

//    @OneToMany(mappedBy = "brand")
//    private List<Car> cars;

    // TODO: Fix proxy
//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("CarBrand{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", models=").append(models);
//        sb.append(", cars=").append(cars);
//        sb.append('}');
//        return sb.toString();
//    }
}
