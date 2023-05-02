package com.capstone.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @NotBlank(message = "user email cannot be blank")
    private String email;

    @NotBlank(message = "user password cannot be blank")
    private String password;
}
