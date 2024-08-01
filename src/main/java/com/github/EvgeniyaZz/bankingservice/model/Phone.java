package com.github.EvgeniyaZz.bankingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.EvgeniyaZz.bankingservice.HasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@ToString(callSuper = true, exclude = "user")
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 7, max = 16)
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference(value = "user-phone")
    private User user;

    public Phone(String number) {
        this.number = number;
    }

    public Phone(Integer id, String number) {
        super(id);
        this.number = number;
    }

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }
}
