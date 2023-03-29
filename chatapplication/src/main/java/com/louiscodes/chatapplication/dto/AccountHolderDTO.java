package com.louiscodes.chatapplication.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Data
public class AccountHolderDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp="\\+\\d{11}")
    private String phoneNumber;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String nickname;

}
