package com.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizservice.dto.QuestionDTO;
import com.quizservice.dto.QuizDTO;
import com.quizservice.dto.Response;
import com.quizservice.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
	QuizService qService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
		return qService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
	}

	@PostMapping("get/{id}")
	public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable Integer id) {
		return qService.getQuizQuestions(id);
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> getScore(@PathVariable Integer id, @RequestBody List<Response> responses) {
		return qService.getScore(id, responses);
	}

}
