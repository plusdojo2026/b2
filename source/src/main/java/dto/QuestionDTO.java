package dto;

import java.io.Serializable;

public class QuestionDTO implements Serializable {

	//フィールド
	private int questionId, qType; //questionId = Question.id(主キー)
	private String question, created_atQcont;
	
	//コンストラクタ
	public QuestionDTO(int questionId, int qType, String question, String created_atQcont) {
		super();
		this.questionId = questionId;
		this.qType = qType;
		this.question = question;
		this.created_atQcont = created_atQcont;
	}

	//ゲッタ、セッタ
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getqType() {
		return qType;
	}

	public void setqType(int qType) {
		this.qType = qType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCreated_atQcont() {
		return created_atQcont;
	}

	public void setCreated_atQcont(String created_atQcont) {
		this.created_atQcont = created_atQcont;
	}
}
