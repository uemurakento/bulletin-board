package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
		//ここにコメントの値を入れる
		List<Comment> comments = new ArrayList<>();
		Article article = new Article(rs.getInt("id"),rs.getString("name"),rs.getString("content"),comments);
		return article;
	};
	
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM "+TABLE_NAME+" ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Article> articles = template.query(sql, param, ARTICLE_ROWMAPPER);
		return articles;
	}

	public void insert(Article article) {
		String sql = "INSERT INTO articles (name,content) values (:name,:content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}
	
	public void deleteById(int id) {
		
	}
}
