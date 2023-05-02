package com.capstone.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    @NotNull(message = "Provide the user_id of the user")
    private Long user_id;
    @NotNull(message = "Provide the question_id to which the answer is provided by the user")
    private Long question_id;
    @NotBlank(message = "Provide the user answer")
    private String answer;
}
