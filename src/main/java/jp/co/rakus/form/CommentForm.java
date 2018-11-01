package jp.co.rakus.form;

/**
 * コメントの内容を受け取るフォーム.
 * 
 * @author kento.uemura
 *
 */
public class CommentForm {
	/** 記事ID */
	private String articleId;
	/** コメント投稿者の名前 */
	private String name;
	/** コメントの内容 */
	private String content;

	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
}