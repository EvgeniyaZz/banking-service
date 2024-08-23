package com.github.EvgeniyaZz.bankingservice.model;

import com.github.EvgeniyaZz.bankingservice.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@ToString(callSuper = true, exclude = "user")
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractBaseEntity implements HasId {

    @Column(name = "amount", nullable = false)
    @Range(min = 0)
    private int amount;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Account(int amount, User user) {
        this.amount = amount;
        this.user = user;
    }
}
