package com.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.questionservice.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findBycategory(String category);

	List<Question> findBydifficultyLevel(String level);

	List<Question> findByquestionTitle(String title);

	@Query(value = "SELECT q.id FROM question q WHERE q.category = :categoryName ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
	List<Integer> findRandomQuestionBycategory(@Param("categoryName") String categoryName, @Param("numQuestions") Integer numQuestions);

}
