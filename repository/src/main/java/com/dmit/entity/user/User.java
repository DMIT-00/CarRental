package com.dmit.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;
    @Column(name = "user_username", unique = true, nullable = false)
    private String username;
    @Column(name = "user_password", nullable = false)
    private String password;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetail userDetail;

    public void addUserDetail(UserDetail userDetail) {
        userDetail.setUser(this);
        this.userDetail = userDetail;
    }

    public void removeUserDetail() {
        if (userDetail != null) {
            userDetail.setUser(null);
            this.userDetail = null;
        }
    }
}
