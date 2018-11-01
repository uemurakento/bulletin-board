package jp.co.rakus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/bulletinBoard")
public class ArticleController {
	
	@RequestMapping("/")
	public String index(Model model) {
		//リポジトリを呼んで記事一覧を初期値としてセットする
		return "bulletinboard";
	}
}
