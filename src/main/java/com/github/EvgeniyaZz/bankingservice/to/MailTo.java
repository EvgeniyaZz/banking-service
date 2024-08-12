package com.github.EvgeniyaZz.bankingservice.to;

import com.github.EvgeniyaZz.bankingservice.HasId;
import com.github.EvgeniyaZz.bankingservice.util.NoHtml;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Добавление/изменение email")
public class MailTo extends BaseTo implements HasId {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    @NoHtml
    String email;

    public MailTo(Integer id, String email) {
        super(id);
        this.email = email;
    }
}