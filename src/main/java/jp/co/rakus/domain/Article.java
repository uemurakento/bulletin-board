package jp.co.rakus.domain;

import java.util.List;

/**
 * 記事を表すドメイン.
 * 
 * @author kento.uemura
 *
 */
public class Article {
	/** 記事id */
	private Integer id;
	/** 記事の投稿者名 */
	private String name;
	/** 記事の内容 */
	private String content;
	/** 記事のコメント*/
	private List<Comment> commentList;
	
	public Article() {}
	public Article(Integer id, String name, String content, List<Comment> commentList) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.commentList = commentList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
}
