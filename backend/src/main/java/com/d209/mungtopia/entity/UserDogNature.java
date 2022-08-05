package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "user_dog_nature", schema = "mungtopia", catalog = "")
public class UserDogNature {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_dog_nature_id")
    private long userDogNatureId;
    @Basic
    @Column(name = "feature")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User userByUserSeq;

    public User getUserByUserSeq() {
        return userByUserSeq;
    }

    public void setUserByUserSeq(User userByUserSeq) {
        this.userByUserSeq = userByUserSeq;
    }
}
