package com.github.EvgeniyaZz.bankingservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.EvgeniyaZz.bankingservice.HasIdAndLogin;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@ToString(callSuper = true, exclude = {"password", "mails", "phones"})
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractBaseEntity implements HasIdAndLogin {

    @NotBlank
    @Size(min = 2, max = 32)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 128)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-mail")
    private List<Mail> mails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-phone")
    private List<Phone> phones;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

    public User(Integer id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String password, Account account, Role role) {
        super(id);
        this.login = login;
        this.password = password;
        this.account = account;
        setRoles(List.of(role));
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles.isEmpty() ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}