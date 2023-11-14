package com.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.dto.QuestionDTO;
import com.questionservice.dto.Response;
import com.questionservice.entity.Question;
import com.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionControler {

	@Autowired
	QuestionService questionService;

	@Autowired
	Environment environment;

	@GetMapping("allQuestons")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("id/{id}")
	public ResponseEntity<Question> getQuestionByid(@PathVariable Integer id) {
		return questionService.getQuestionByid(id);
	}

	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
		return questionService.getQuestionByCategory(category);
	}

	@GetMapping("difficultylevel/{level}")
	public ResponseEntity<List<Question>> getQuestionBylevel(@PathVariable String level) {
		return questionService.getQuestionBylevel(level);
	}

	@GetMapping("questiontitle/{title}")
	public ResponseEntity<List<Question>> getQuestionByTitle(@PathVariable String title) {
		return questionService.getQuestionByTitle(title);
	}

	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
		return questionService.updateQuestion(id, question);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}

	// it will generate the questions for the quiz
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
			@RequestParam Integer numQuestions) {
		return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	}

	// it will help in getting the questions for the particular id
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionDTO>> getQuestionFromId(@RequestBody List<Integer> questionIds) {
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionFromId(questionIds);
	}

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response) {
		return questionService.getScore(response);
	}

}
