package com.capstone.doconnect.controller;

import com.capstone.doconnect.dto.AnswerDTO;
import com.capstone.doconnect.dto.GetAnswerDetailsDTO;
import com.capstone.doconnect.dto.QuestionDTO;
import com.capstone.doconnect.dto.UserLoginDTO;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;
import com.capstone.doconnect.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        return userService.userLogin(email, password);
    }

    @GetMapping("/logout/{userId}")
    public User userLogout(@PathVariable Long userId) {
        return userService.userLogout(userId);
    }

    @PostMapping("/register")
    public User userRegister(@Valid @RequestBody User user) {
        return userService.userRegister(user);
    }

    @PostMapping("/askQuestion")
    public Question askQuestion(@Valid @RequestBody QuestionDTO askQuestionDTO) {
        return userService.askQuestion(askQuestionDTO);
    }

    @PostMapping("/giveAnswer")
    public Answer giveAnswer(@Valid @RequestBody AnswerDTO postAnswerDTO) {
        return userService.postAnswer(postAnswerDTO);
    }

    @GetMapping("/searchQuestion/{question}")
    public List<Question> searchQuestion(@PathVariable String question) {
        return userService.findQuestion(question);
    }

    @GetMapping("/getAnswerDetails/{questionId}")
    public List<GetAnswerDetailsDTO> getAnswerDetails(@PathVariable Long questionId) {
        return userService.findAnswerDetailByQuestionId(questionId);
    }

    @GetMapping("/getQuestionById/{question_id}")
    public Question getQuestionById(@PathVariable Long question_id) { return userService.getQuestionById(question_id); }

    @GetMapping("/getQuestions/{topic}")
    public List<Question> getQuestions(@PathVariable String topic) {
        return userService.getQuestionsByTopic(topic);
    }


}

