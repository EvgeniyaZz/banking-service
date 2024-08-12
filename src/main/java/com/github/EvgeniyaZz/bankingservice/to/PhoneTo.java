package com.github.EvgeniyaZz.bankingservice.to;

import com.github.EvgeniyaZz.bankingservice.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Добавление/изменение phone")
public class PhoneTo extends BaseTo implements HasId {

    @NotBlank
    @Size(min = 7, max = 16)
    String number;

    public PhoneTo(Integer id, String number) {
        super(id);
        this.number = number;
    }
}