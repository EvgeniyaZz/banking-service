package com.github.EvgeniyaZz.bankingservice.to;

import com.github.EvgeniyaZz.bankingservice.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Добавление данных о пользователе(ФИО, дата рождения)")
public class UserDetailTo extends BaseTo implements HasId {

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "firstname")
    String firstname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "lastname")
    String lastname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "middlename")
    String middlename;

    @NotNull
    @Column(name = "birth_date")
    LocalDate birthDate;

    public UserDetailTo(Integer id, String firstname, String lastname, String middlename, LocalDate birthDate) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
    }
}