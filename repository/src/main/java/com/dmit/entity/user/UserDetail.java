package com.dmit.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user_detail")
public class UserDetail {
    @Id
    @Column(name = "user_detail_id", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "user_first_name", nullable = false)
    private String firstName;
    @Column(name = "user_last_name", nullable = false)
    private String lastName;
    @Column(name = "user_phoneNumber", unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "user_credit_card", unique = true, nullable = false)
    private String creditCard;
    @Column(name = "user_birth_date", nullable = false)
    private Date birthDate;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_detail_id")   //same name as id @Column
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDetail that = (UserDetail) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
