package com.github.EvgeniyaZz.bankingservice.model;

import com.github.EvgeniyaZz.bankingservice.HasId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    public Account(int account) {
        this.account = account;
    }

    public Account(Integer id, int account) {
        super(id);
        this.account = account;
    }
}
