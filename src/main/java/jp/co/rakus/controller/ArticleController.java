package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.form.CommentForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

/**
 * 掲示板を表示するコントローラー.
 * 
 * @author kento.uemura
 *
 */
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

	/**
	 * 画面を表示する.
	 * 
	 * @param model
	 *            モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("/")
	public String index(Model model) {
		// List<Article> articles = articleRepository.findAll();
		// for(Article article:articles) {
		// article.setCommentList(commentRepository.findByArticleId(article.getId()));
		// }
		List<Article> articles = articleRepository.findAllAtOnceSql();
		model.addAttribute("articles", articles);
		return "bulletinboard";
	}

	/**
	 * 記事を追加をする.
	 * 
	 * @param model
	 *            モデル
	 * @param form
	 *            入力された記事情報.
	 * @param result
	 *            エラーメッセージを格納
	 * @return indexにフォワード
	 */
	@RequestMapping("/insertarticle")
	public String insertArticle(Model model, @Validated ArticleForm form, BindingResult result) {
		if (result.hasErrors()) {
			return index(model);
		}
		Article article = new Article(null, form.getName(), form.getContent(), null);
		articleRepository.insert(article);
		return "redirect:/bulletinboard/";
	}

	/**
	 * コメントを追加する.
	 * 
	 * @param model
	 *            モデル
	 * @param form
	 *            入力されたコメント情報
	 * @param result
	 *            エラーメッセージを格納
	 * @return indexにフォワード
	 */
	@RequestMapping("/insertcomment")
	public String insertComment(Model model, @Validated CommentForm form, BindingResult result) {
		if (result.hasErrors()) {
			return index(model);
		}
		Comment comment = new Comment(null, form.getName(), form.getContent(), Integer.valueOf(form.getArticleId()));
		commentRepository.insert(comment);
		return "redirect:/bulletinboard/";
	}

	/**
	 * 記事とコメントを削除する.
	 * 
	 * @param model
	 *            モデル
	 * @param articleId
	 *            削除する記事番号
	 * @return indexメソッド
	 */
	@RequestMapping("/deletearticle")
	public String deleteArticle(Model model, Integer articleId) {
		// commentRepository.deleteByArticleId(articleId);
		// articleRepository.deleteById(articleId);
		articleRepository.deleteByIdOnceSql(articleId);
		return index(model);
	}
}
