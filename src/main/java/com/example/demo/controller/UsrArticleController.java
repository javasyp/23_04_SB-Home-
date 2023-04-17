package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

// /usr/article/getArticle?id=1
// 결과 -> 1번 글의 객체 데이터 or 1번 글은 존재하지 않습니다

@Controller
public class UsrArticleController {
	int lastArticleId;
	List<Article> articles;
	
	// 생성자
	public UsrArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	// 1. 서비스 메서드
	// 테스트 데이터
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			int id = lastArticleId + 1;
			String title = "제목" + i;
			String body = "내용" + i;
			
			Article article = new Article(id, title, body);
			articles.add(article);
			
			lastArticleId++;
		}
	}
	
	// 작성
	public Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;

		return article;
	}
	
	// 객체 가져오기
	private Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {	// article.id => private이라 접근 불가. getter 메서드 사용
				return article;
			}
		}
		return null;
	}
	
	// 삭제
	private void deleteArticle(int id) {
		Article article = getArticle(id);
		
		articles.remove(article);
	}
	
	// 수정
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);
		
		article.setTitle(title); 	// article.title => private이라 접근 불가. setter 메서드 사용
		article.setBody(body); 		// article.body => private이라 접근 불가. setter 메서드 사용
	}
	
	// 2. 액션 메서드
	// 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		Article article = writeArticle(title, body);
		
		return article;
	}
	
	// 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = getArticle(id);
		
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		deleteArticle(id);
		
		return id + "번 글이 삭제되었습니다.";
	}
	
	// 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {		// Object
		Article article = getArticle(id);
		
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		modifyArticle(id, title, body);
		
		return article;
	}

	// 목록
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	
	// 상세보기
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleDetail(int id) {	// Object 사용 시 모든 타입 리턴 가능
		Article article = getArticle(id);
		
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		return article;
	}
	
}
