package com.capstone.doconnect.sevices;

import com.capstone.doconnect.dto.AnswerDTO;
import com.capstone.doconnect.dto.GetAnswerDetailsDTO;
import com.capstone.doconnect.dto.QuestionDTO;
import com.capstone.doconnect.entities.Answer;
import com.capstone.doconnect.entities.Question;
import com.capstone.doconnect.entities.User;
import com.capstone.doconnect.repository.model.Message;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    public User userLogin(String email, String password);

    public User userLogout(Long userId);

    public User userRegister(@Valid User user);

    public Question askQuestion(@Valid QuestionDTO askQuestionDTO);

    public Answer postAnswer(@Valid AnswerDTO postAnswerDTO);

    public List<Question> findQuestion(String question);

    public List<Answer> getAnswers(Long questionId);

    public List<Question> getQuestionsByTopic(String topic);

    public Question getQuestionById(Long question_id);

    public List<GetAnswerDetailsDTO> findAnswerDetailByQuestionId(Long question_id);
}
