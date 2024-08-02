package com.github.EvgeniyaZz.bankingservice.to;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import com.github.EvgeniyaZz.bankingservice.HasIdAndLogin;
import com.github.EvgeniyaZz.bankingservice.util.NoHtml;

@Value
@ToString(callSuper = true, exclude = "password")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Запрос на регистрацию")
public class UserTo extends BaseTo implements HasIdAndLogin {

    @NotBlank
    @Size(min = 2, max = 32)
    String login;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    @NotBlank
    @Size(min = 7, max = 16)
    String number;

    @Email
    @NotBlank
    @Size(max = 100)
    @NoHtml
    String email;

    int account;

    public UserTo(Integer id, String login, String password, String number, String email, int account) {
        super(id);
        this.login = login;
        this.password = password;
        this.number = number;
        this.email = email;
        this.account = account;
    }
}