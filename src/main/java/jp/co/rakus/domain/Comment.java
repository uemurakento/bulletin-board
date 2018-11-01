package jp.co.rakus.domain;

/**
 * コメントを表すドメイン.
 * 
 * @author kento.uemura
 *
 */
public class Comment {
	/** コメントのid */
	private Integer id;
	/** コメント者の名前 */
	private String name;
	/** コメントの内容*/
	private String content;
	/** コメントの記事id*/
	private Integer articleId;

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
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
}
