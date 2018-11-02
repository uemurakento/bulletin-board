package jp.co.rakus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Comment;

/**
 * コメントを操作するリポジトリ
 * 
 * @author kento.uemura
 *
 */
@Repository
public class CommentRepository {
	public static final String TABLE_NAME = "comments";
	@Autowired
	NamedParameterJdbcTemplate template;
	private static final RowMapper<Comment> COMMENT_ROWMAPPER = (rs,i) -> {
		Comment comment = new Comment(rs.getInt("id"),rs.getString("name"),rs.getString("content"),rs.getInt("article_id"));
		return comment;
	};
	
	public List<Comment> findByArticleId(Integer id){
		String sql = "SELECT id,name,content,article_id FROM "+TABLE_NAME+" WHERE article_id=:id ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Comment> comments = template.query(sql, param, COMMENT_ROWMAPPER);
		return comments;
	}
	
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String sql = "INSERT INTO "+TABLE_NAME+" (name,content,article_id) VALUES (:name,:content,:articleId);";
		template.update(sql, param);
	}
}
