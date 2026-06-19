package dto;

import java.io.Serializable;

public class QuestionDTO implements Serializable {

	//フィールド
	private int id, qType; //id = Question.id(主キー)
	private String question, created_at;
	
	//コンストラクタ
	public QuestionDTO(int id, String question, int qType, String created_at) {
		super();
		this.id = id;
		this.question = question;
		this.qType = qType;
		this.created_at = created_at;
	}

	//ゲッタ、セッタ
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQType() {
		return qType;
	}

	public void setQType(int qType) {
		this.qType = qType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
