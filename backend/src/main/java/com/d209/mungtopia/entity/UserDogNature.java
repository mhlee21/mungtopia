package com.d209.mungtopia.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "user_dog_nature", schema = "mungtopia", catalog = "")
@NoArgsConstructor
public class UserDogNature {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_dog_nature_id")
    private long userDogNatureId;

    @Column(name = "nature1")
    private int nature1;

    @Column(name = "nature2")
    private int nature2;

    @Column(name = "nature3")
    private int nature3;

    @Column(name = "nature4")
    private int nature4;

    @Column(name = "nature5")
    private int nature5;

    @Column(name = "nature6")
    private int nature6;

    // referencedColumnName : 외래 키가 참조하는 대상 테이블의 컬럼명
    @OneToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User user;

    // 비즈니스 로직
    public void saveUser(User user, List<Integer> nature){
        this.user = user;
        for (int i = 0; i < nature.size(); i++) {
            switchResult(i + 1,nature.get(i));
        }
    }

    private void switchResult(int index, int result){
        switch (index){
            case 1:
                this.nature1 = result;
                break;
            case 2:
                this.nature2 = result;
                break;
            case 3:
                this.nature3 = result;
                break;
            case 4:
                this.nature4 = result;
                break;
            case 5:
                this.nature5 = result;
                break;
            case 6:
                this.nature6 = result;
                break;
        }
    }
}
