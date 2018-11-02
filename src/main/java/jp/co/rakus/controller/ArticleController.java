package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.form.CommentForm;
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
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
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
	
	@RequestMapping("/insertarticle")
	public String insertArticle(Model model,ArticleForm form) {
		Article article = new Article(null,form.getName(),form.getContent(),null);
		articleRepository.insert(article);
		return index(model);
	}
	
	@RequestMapping("/insertcomment")
	public String insertComment(Model model,CommentForm form) {
		Comment comment = new Comment(null,form.getName(),form.getContent(),Integer.valueOf(form.getArticleId()));
		commentRepository.insert(comment);
		return index(model);
	}
}
