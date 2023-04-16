package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

// UsrArticleController

// 1. /usr/article/doWrite
// --> id, title, body로 이루어진 게시글 객체 생성 (title, body는 파라미터)
// --> Article ArrayList에 add

// 2. /usr/article/getArticles
// --> add 했던 게시글들 출력

@Controller
public class UsrArticleController {
	int lastArticleId;
	List<Article> articles;
	
	public UsrArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		
		lastArticleId++;

		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	
}
