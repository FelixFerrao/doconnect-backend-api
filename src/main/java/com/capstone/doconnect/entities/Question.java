package com.capstone.doconnect.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message="question string cannot be empty")
    private String question;
    @NotBlank(message="topic string cannot be empty")
    private String topic;
    private boolean isApproved = false;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "answer")
    private List<Answer> answerList;
}
