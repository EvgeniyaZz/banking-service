package com.github.EvgeniyaZz.bankingservice.model;

import com.github.EvgeniyaZz.bankingservice.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractBaseEntity implements HasId {

    @Column(name = "account", nullable = false)
    @Range(min = 0)
    private int account;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Account(int account, User user) {
        this.account = account;
        this.user = user;
    }
}
