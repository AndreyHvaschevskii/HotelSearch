package com.hotelsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Email or phone number must not be empty")
    private String email;
    @NotBlank(message = "First and last name must not be empty")
    private String name;
    @NotBlank(message = "Username must not be empty")
    private String login;
    @NotBlank(message = "Password must not be empty")
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }
}
