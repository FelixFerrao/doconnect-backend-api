package com.capstone.doconnect.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private boolean isActive = false;

    @OneToMany(mappedBy = "question")
    private List<Question> questionList;

    @OneToMany(mappedBy = "answer")
    private List<Answer> answerList;
}
