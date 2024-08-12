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

import java.time.LocalDate;

@ToString(callSuper = true, exclude = "user")
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "middlename", nullable = false)
    private String middlename;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonBackReference(value = "user-user_detail")
    private User user;

    public UserDetail(Integer id, String firstname, String lastname, String middlename, LocalDate birthDate) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
    }

    public UserDetail(String firstname, String lastname, String middlename, LocalDate birthDate, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
        this.user = user;
    }
}
