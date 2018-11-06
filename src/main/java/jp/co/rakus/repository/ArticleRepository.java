package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
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
	
	private static final RowMapper<Article> ARTICLE_ROWMAPPER = (rs, i) -> {
		List<Comment> comments = new ArrayList<>();
		Article article = new Article(
				rs.getInt("id"), 
				rs.getString("name"),
				rs.getString("content"),
				comments);
		
		return article;
	};
	
	private static final ResultSetExtractor<List<Article>> RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articles = new ArrayList<>();
		Integer previousId = 0;
		List<Comment> comments = null;
		Article article = null;

		while (rs.next()) {
			Integer articleId = rs.getInt("article_id");

			if (previousId != articleId) {
				article = new Article(
						articleId,
						rs.getString("article_name"),
						rs.getString("article_content"),
						new ArrayList<>());
				
				comments = article.getCommentList();
				articles.add(article);
			}
			
			if (rs.getInt("comment_id") != 0) {
				Comment comment = new Comment(
						rs.getInt("comment_id"),
						rs.getString("comment_name"),
						rs.getString("comment_content"),
						articleId);
				
				comments.add(comment);
			}
			
			previousId = articleId;

		}
		return articles;
	};

	/**
	 * 記事の全件検索.
	 * 
	 * @return
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM " + TABLE_NAME + " ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Article> articles = template.query(sql, param, ARTICLE_ROWMAPPER);
		return articles;
	}

	/**
	 * 記事の追加.
	 * 
	 * @param article
	 *            追加する記事情報を持ったドメイン
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name,content) values (:name,:content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * 記事の削除.
	 * 
	 * @param id
	 *            削除する記事番号
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * SQL文一回で記事とコメントを削除する.
	 * 
	 * @param id
	 *            削除する記事番号
	 */
	public void deleteByIdOnceSql(int id) {
		String sql = "WITH deleted AS (DELETE FROM " + CommentRepository.TABLE_NAME
				+ " WHERE article_id=:id) delete from " + TABLE_NAME + " where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

	/**
	 * 記事、コメントの全件検索を一括で行う.
	 * 
	 * @return 検索された記事、コメント情報を格納したlist
	 */
	public List<Article> findAllAtOnceSql() {
		String sql = "SELECT a.id article_id,a.name article_name,a.content article_content,c.id comment_id,c.name comment_name,c.content comment_content FROM "
				+ TABLE_NAME + " a LEFT OUTER JOIN " + CommentRepository.TABLE_NAME
				+ " c ON a.id = c.article_id ORDER BY a.id DESC ,c.id DESC;";

		List<Article> articles = template.query(sql, RESULT_SET_EXTRACTOR);
		// List<Article> articles = template.query(sql,new
		// ResultSetExtractor<List<Article>>() {
		// public List<Article> extractData(ResultSet rs) throws
		// SQLException,DataAccessException{
		// List<Article> articles = new ArrayList<>();
		// while(rs.next()) {
		// Integer article_id = rs.getInt("article_id");
		// Article article;
		// List<Comment> comments;
		// if(articles.size() < article_id) {
		// article = new
		// Article(article_id,rs.getString("article_name"),rs.getString("article_content"),null);
		// comments = new ArrayList<>();
		// }else {
		// article = articles.get(article_id - 1);
		// comments = article.getCommentList();
		// }
		// Comment comment = new
		// Comment(rs.getInt("comment_id"),rs.getString("comment_name"),rs.getString("comment_content"),article_id);
		// comments.add(comment);
		// article.setCommentList(comments);
		// if(articles.size() < article_id) {
		// articles.add(article);
		// }
		// }
		// return articles;
		// }
		// });
		return articles;
	}
}
