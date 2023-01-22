package com.dmit.entity.order;

import com.dmit.entity.car.Car;
import com.dmit.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "CHAR(36)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "order_end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "order_total_price", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void removeUser() {
        if (user != null) {
            user.getOrders().remove(this);
            this.user = null;
        }
    }

    public void addCar(Car car) {
        this.car = car;
        car.getOrders().add(this);
    }

    public void removeCar() {
        if (car != null) {
            car.getOrders().remove(this);
            this.car = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
