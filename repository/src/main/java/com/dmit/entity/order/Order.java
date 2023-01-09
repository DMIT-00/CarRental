package com.dmit.entity.order;

import com.dmit.entity.car.Car;
import com.dmit.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
    private Timestamp startDate;

    @Column(name = "order_hours", nullable = false)
    private Long numberOfHours;

    @Column(name = "order_total_price", nullable = false)
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
