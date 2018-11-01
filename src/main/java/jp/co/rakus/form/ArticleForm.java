package jp.co.rakus.form;


/**
 * 記事の内容を受け取るフォーム.
 * 
 * @author kento.uemura
 *
 */
public class ArticleForm {
	/** 記事の投稿者名 */
	private String name;
	/** 記事の内容 */
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
