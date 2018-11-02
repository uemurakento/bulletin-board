package jp.co.rakus.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事の内容を受け取るフォーム.
 * 
 * @author kento.uemura
 *
 */
public class ArticleForm {
	/** 記事の投稿者名 */
	@NotBlank(message="名前を入力してください")
	@Size(max=50,message="名前は５０字以内で入力してください")
	private String name;
	/** 記事の内容 */
	@NotBlank(message="記事を入力してください")
	private String content;

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
