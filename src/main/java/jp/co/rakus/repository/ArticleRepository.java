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
		List<Comment> comments = new ArrayList<>();
		Article article = new Article(rs.getInt("id"),rs.getString("name"),rs.getString("content"),comments);
		return article;
	};
	
	/**
	 * 記事の全件検索.
	 * 
	 * @return
	 */
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM "+TABLE_NAME+" ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Article> articles = template.query(sql, param, ARTICLE_ROWMAPPER);
		return articles;
	}

	/**
	 * 記事の追加.
	 * 
	 * @param article 追加する記事情報を持ったドメイン
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name,content) values (:name,:content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}
	
	/**
	 * 記事の削除.
	 * 
	 * @param id 削除する記事番号
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
