package com.capstone.doconnect.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @NotBlank(message = "Provide user details")
    private String userDetails;
    @NotBlank(message = "Provide the message")
    private String message;
}
