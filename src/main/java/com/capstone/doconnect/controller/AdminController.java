package com.capstone.doconnect.controller;

import com.capstone.doconnect.dto.ResponseDTO;
import com.capstone.doconnect.entities.Admin;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;
import com.capstone.doconnect.sevices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login/{email}/{password}")
    public Admin adminLogin(@PathVariable String email, @PathVariable String password) {
        return adminService.adminLogin(email, password);
    }

    @GetMapping("/logout/{adminId}")
    public String adminLogout(@PathVariable Long adminId) {
        return adminService.adminLogout(adminId);
    }

    @PostMapping("/register")
    public Admin adminRegister(@Valid @RequestBody Admin admin) {
        return adminService.adminRegister(admin);
    }

    @GetMapping("/getUnApprovedQuestions")
    public List<Question> getUnApprovedQuestions() {
        return adminService.getUnApprovedQuestions();
    }

    @GetMapping("/getUnApprovedAnswers")
    public List<Answer> getUnApprovedAnswers() {
        return adminService.getUnApprovedAnswers();
    }

    @PutMapping("/approveQuestion/{questionId}")
    public Question approveQuestion(@PathVariable Long questionId) {
        return adminService.approveQuestion(questionId);
    }

    @PutMapping("/approveAnswer/{answerId}")
    public Answer approveAnswer(@PathVariable Long answerId) {
        return adminService.approveAnswer(answerId);
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    public ResponseDTO deleteQuestion(@PathVariable Long questionId) {
        return adminService.deleteQuestion(questionId);
    }

    @DeleteMapping("/deleteAnswer/{answerId}")
    public ResponseDTO deleteAnswer(@PathVariable Long answerId) {
        return adminService.deleteAnswer(answerId);
    }

    @GetMapping("/getUser/{email}")
    public User getUser(@PathVariable String email) {
        return adminService.getUser(email);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser() {
        return adminService.getAllUser();
    }

}

