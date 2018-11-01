package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;

/**
 * 記事を操作するリポジトリ.
 * 
 * @author kento.uemura
 *
 */
@Repository
public class ArticleRepository {
	private static final String TABLE_NAME = "articles";
	@Autowired
	public NamedParameterJdbcTemplate template;
	private static final RowMapper<Article> ARTICLE_ROWMAPPER = (rs,i) ->{
		//ここにコメントを入れる
		List<Comment> comments = new ArrayList<>();
		Article article = new Article(rs.getInt("id"),rs.getString("name"),rs.getString("content"),comments);
		return article;
	};
	
	public List<Article> findAll(){
		return null;
	}

	public void insert(Article article) {
		
	}
	
	public void deleteById(int id) {
		
	}
}
