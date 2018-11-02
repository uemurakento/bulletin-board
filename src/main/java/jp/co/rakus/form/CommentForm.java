package jp.co.rakus.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank(message="名前を入力してください")
	@Size(max=50,message="名前は５０字以内で入力してください")
	private String name;
	/** コメントの内容 */
	@NotBlank(message="記事を入力してください")
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
