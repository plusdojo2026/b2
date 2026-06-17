package dto;

import java.io.Serializable;

public class DailyDTO implements Serializable {
	/*
	 * dailyRec_id : 主キー、auto increment
	 * update_at, created_atはStringで実装、ソートなど作るときにはdate型へ変更必須
	 * 
	 */

	//フィールド----------------------------------------------
	//DiaryRecテーブル
	private int dailyRec_id, emotion_id;
	private String freeForm, photo, positive, update_at, created_at;
	//質問内容
	private int question_id; //主キー
	private String question, created_atQcont;
	//質問解答
	private int questionAns_id; //主キー
	private String questionAns, created_atQans;
	
	//コンストラクタ--------------------------------------------
	public DailyDTO(int dailyRec_id, String freeForm, String photo, String positive, int emotion_id, String update_at,
			String created_at, int question_id, String question, String created_atQcont, int questionAns_id, String questionAns, String created_atQans) {
		super();
		this.dailyRec_id = dailyRec_id;
		this.freeForm = freeForm;
		this.photo = photo;
		this.positive = positive;
		this.emotion_id = emotion_id;
		this.update_at = update_at;
		this.created_at = created_at;
		
		this.question_id = question_id;
		this.question = question;
		this.created_atQcont = created_atQcont;
		this.questionAns_id = questionAns_id;
		this.created_atQans = created_atQans;
	}
	//一応入力なしを作ってみた
	public DailyDTO() {
		super();
		this.dailyRec_id = 0;
		this.freeForm = "";
		this.photo = "";
		this.positive = "";
		this.emotion_id = 0;
		this.update_at = "";
		this.created_at = "";
		
		this.question_id = 0;
		this.question = "";
		this.created_atQcont = "";
		
		this.questionAns_id = 0;
		this.created_atQans = "";
	}
	
	//ゲッタ、セッタ------------------------------------------------
	public int getDailyRec_id() {
		return dailyRec_id;
	}
	public void setDailyRec_id(int dailyRec_id) {
		this.dailyRec_id = dailyRec_id;
	}
	public String getFreeForm() {
		return freeForm;
	}
	public void setFreeForm(String freeForm) {
		this.freeForm = freeForm;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPositive() {
		return positive;
	}
	public void setPositive(String positive) {
		this.positive = positive;
	}
	public int getEmotion() {
		return emotion_id;
	}
	public void setEmotion(int emotion_id) {
		this.emotion_id = emotion_id;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
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
	public int getQuestionAns_id() {
		return questionAns_id;
	}
	public void setQuestionAns_id(int questionAns_id) {
		this.questionAns_id = questionAns_id;
	}
	public String getQuestionAns() {
		return questionAns;
	}
	public void setQuestionAns(String questionAns) {
		this.questionAns = questionAns;
	}
	public String getCreated_atQans() {
		return created_atQans;
	}
	public void setCreated_atQans(String created_atQans) {
		this.created_atQans = created_atQans;
	}
	
}
