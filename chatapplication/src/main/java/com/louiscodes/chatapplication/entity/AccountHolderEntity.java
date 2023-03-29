package com.louiscodes.chatapplication.entity;

import com.louiscodes.chatapplication.shared.converter.OptionalStringConverter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
@Entity
@Table(name = "account_holder")
public class AccountHolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Invalid email address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*$", message = "Invalid phone number")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "Username cannot be blank")
    @Length(min = 6, message = "Username must be at least 6 characters long")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 6, message = "Password must be at least 8 characters long")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname")
    @Convert(converter = OptionalStringConverter.class)
    private Optional<String> nickname;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactsEntity> contacts = new HashSet<>();

    public AccountHolderEntity() {
    }

    public AccountHolderEntity(String firstName, String lastName, String email, String phoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public AccountHolderEntity(String firstName, String lastName, String email, String phoneNumber, String username, String password, Optional<String> nickname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
