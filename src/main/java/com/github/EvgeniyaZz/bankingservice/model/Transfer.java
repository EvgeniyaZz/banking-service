package com.github.EvgeniyaZz.bankingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.EvgeniyaZz.bankingservice.HasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "transfer")
public class Transfer extends AbstractBaseEntity implements HasId {

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @Column(name = "amount", nullable = false)
    @Range(min = 0)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JoinColumn(name = "account_from_id", nullable = false)
    private Account accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JoinColumn(name = "account_to_id", nullable = false)
    private Account accountTo;

    public Transfer(int amount) {
        this.amount = amount;
    }

    public Transfer(int amount, Account accountFrom, Account accountTo) {
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }
}
