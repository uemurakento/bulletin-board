package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

@Controller
@Transactional
@RequestMapping("/bulletinboard")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Article> articles = articleRepository.findAll();
		for(Article article:articles) {
			article.setCommentList(commentRepository.findByArticleId(article.getId()));
		}
		model.addAttribute("articles", articles);
		return "bulletinboard";
	}
	
	public String insertArticle(Model model) {
		return index(model);
	}
}
