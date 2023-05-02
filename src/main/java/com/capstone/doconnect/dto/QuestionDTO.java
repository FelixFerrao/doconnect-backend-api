package com.capstone.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    @NotNull(message = "Provide the user Id of user asking the question")
    private Long user_id;
    @NotBlank(message = "Provide the question asked by the user")
    private String user_question;
    @NotBlank(message = "Provide the topic of the question asked")
    private String topic;
}
