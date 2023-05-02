package com.capstone.doconnect.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Lob
    @NotBlank(message="answer string cannot be empty")
    private String answer;
    private boolean isApproved = false;

    @OneToOne
    private User user;

    @OneToOne
    private Question question;
}
