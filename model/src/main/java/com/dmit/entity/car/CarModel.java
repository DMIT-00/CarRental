package com.dmit.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

//    @OneToMany(mappedBy = "model")
//    private List<Car> cars;

//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("CarModel{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", brand=").append(brand);
//        sb.append(", cars=").append(cars);
//        sb.append('}');
//        return sb.toString();
//    }
}
