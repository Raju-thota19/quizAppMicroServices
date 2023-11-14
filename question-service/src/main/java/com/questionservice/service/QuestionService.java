package com.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.dto.QuestionDTO;
import com.questionservice.dto.Response;
import com.questionservice.entity.Question;
import com.questionservice.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepository.findBycategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionBylevel(String level) {
		try {
			return new ResponseEntity<>(questionRepository.findBydifficultyLevel(level), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByTitle(String title) {
		try {
			return new ResponseEntity<>(questionRepository.findByquestionTitle(title), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> getQuestionByid(Integer id) {
		try {

			Question q = questionRepository.findById(id).get();
			if (q != null) {
				return new ResponseEntity<>(q, HttpStatus.OK);
			} else {
				System.out.println("Question with this id is not available " + id);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> addQuestion(Question question) {

		questionRepository.save(question);
		return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
	}

	public ResponseEntity<String> updateQuestion(Integer id, Question question) {
		try {
			Question q = questionRepository.findById(id).get();
			q.setCategory(question.getCategory());
			q.setDifficultyLevel(question.getDifficultyLevel());
			q.setOption1(question.getOption1());
			q.setOption2(question.getOption2());
			q.setOption3(question.getOption3());
			q.setOption4(question.getOption4());
			q.setQuestionTitle(question.getQuestionTitle());
			q.setRightAnswer(question.getRightAnswer());
			questionRepository.save(q);
			return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Question with this id is not available", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		try {
			Question q = questionRepository.findById(id).get();
			questionRepository.delete(q);
			return new ResponseEntity<>("Successfully deleted", HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Question with this id is not available", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> question = questionRepository.findRandomQuestionBycategory(categoryName, numQuestions);
		return new ResponseEntity<>(question, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionDTO>> getQuestionFromId(List<Integer> questionIds) {

		List<QuestionDTO> dtos = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		for (Integer id : questionIds) {
			questions.add(questionRepository.findById(id).get());
		}

		for (Question q : questions) {
			QuestionDTO dto = new QuestionDTO();
			dto.setId(q.getId());
			dto.setOption1(q.getOption1());
			dto.setOption2(q.getOption2());
			dto.setOption3(q.getOption3());
			dto.setOption4(q.getOption4());
			dto.setQuestionTitle(q.getQuestionTitle());
			dtos.add(dto);
		}

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> response) {

		int result = 0;
		for (Response responses : response) {
			Question question = questionRepository.findById(responses.getId()).get();
			if (responses.getResponse().equals(question.getRightAnswer()))
				result++;

		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
