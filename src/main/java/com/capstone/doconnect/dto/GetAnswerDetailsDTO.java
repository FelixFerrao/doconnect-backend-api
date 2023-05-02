package com.capstone.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAnswerDetailsDTO {

    @NotNull(message = "Answer Id cannot be null")
    private Long answer_id;

    @NotBlank(message = "Answer cannot be blank")
    private String answer;

    @NotNull(message = "approved details cannot be null")
    private boolean isApproved;

    @NotNull(message = "User field cannot be empty")
    private Long user_id;

    @NotBlank(message = "User name cannot be blank")
    private String username;

    @NotNull(message = "Question should be associated with the answer")
    private Long question_id;
}
