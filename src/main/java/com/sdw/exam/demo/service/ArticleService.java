package com.sdw.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sdw.exam.demo.repository.ArticleRepository;
import com.sdw.exam.demo.vo.Article;

@Service
public class ArticleService {
	ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		
		articleRepository.makeTestData();
	}

	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}
	
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);

	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
}