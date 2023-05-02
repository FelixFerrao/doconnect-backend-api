package com.capstone.doconnect.sevices;

import com.capstone.doconnect.dto.ResponseDTO;
import com.capstone.doconnect.entities.Admin;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;

import java.util.List;

public interface AdminService {

    public Admin adminLogin(String email, String password);

    public String adminLogout(Long adminId);

    public Admin adminRegister(Admin admin);

    public List<Question> getUnApprovedQuestions();

    public List<Answer> getUnApprovedAnswers();

    public Question approveQuestion(Long questionId);

    public Answer approveAnswer(Long answerId);

    public ResponseDTO deleteQuestion(Long questionId);

    public ResponseDTO deleteAnswer(Long answerId);

    public User getUser(String email);

    public List<User> getAllUser();
}
