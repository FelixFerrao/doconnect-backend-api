package com.capstone.doconnect.serviceImpl;

import com.capstone.doconnect.dto.ResponseDTO;
import com.capstone.doconnect.entities.Admin;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;
import com.capstone.doconnect.exceptions.DuplicateRecord;
import com.capstone.doconnect.exceptions.NotFound;
import com.capstone.doconnect.repository.AdminRepo;
import com.capstone.doconnect.repository.AnswerRepo;
import com.capstone.doconnect.repository.QuestionRepo;
import com.capstone.doconnect.repository.UserRepo;
import com.capstone.doconnect.sevices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    AnswerRepo answerRepo;

    @Override
    public Admin adminLogin(String email, String password) {
        Admin admin = adminRepo.findByEmail(email);
        if(Objects.isNull(admin))
            throw new NotFound();
        if(admin.getPassword().equals(password)) {
            admin.setActive(true);
            adminRepo.save(admin);
        } else
            throw new NotFound();
        return admin;
    }

    @Override
    public String adminLogout(Long adminId) {
        Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new NotFound("Admin with ID" + adminId + "not found!"));
        admin.setActive(false);
        adminRepo.save(admin);
        return "Logged out successfully!";
    }

    @Override
    public Admin adminRegister(Admin admin) {
        Admin tempAdmin = adminRepo.findByEmail(admin.getEmail());
        if(Objects.isNull(tempAdmin))
            adminRepo.save(admin);
        else
            throw new DuplicateRecord("Admin already registered!");
        return admin;
    }

    @Override
    public List<Question> getUnApprovedQuestions() {
        return questionRepo.getUnapprovedQuestions();
    }

    @Override
    public List<Answer> getUnApprovedAnswers() {
        return answerRepo.getUnapprovedAnswers();
    }

    @Override
    public Question approveQuestion(Long questionId) {
        Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question with question ID" + questionId + "not found!"));
        question.setApproved(true);
        questionRepo.save(question);
        return question;
    }

    @Override
    public Answer approveAnswer(Long answerId) {
        Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Question with question ID" + answerId + "not found!"));
        answer.setApproved(true);
        answerRepo.save(answer);
        return answer;
    }

    @Override
    public ResponseDTO deleteQuestion(Long questionId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question not found"));
        questionRepo.delete(question);
        responseDTO.setMsg("Question removed!");
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteAnswer(Long answerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Answer answer =answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Answer not found!"));
        answerRepo.delete(answer);
        responseDTO.setMsg("Answer removed!");
        return responseDTO;
    }

    @Override
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
