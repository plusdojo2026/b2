package dto;

import java.io.Serializable;

public class DailyDTO implements Serializable {
	/*
	 * id : 主キー、auto increment
	 * update_at, created_atはStringで実装、ソートなど作るときにはdate型へ変更必須
	 * 
	 */

	//フィールド----------------------------------------------
	//DiaryRecテーブル
	private int id, user_id, emotion_id;
	private String freeForm, photo, positive, update_at, created_at;
	private double negativeRate, positiveRate, activeIndex;
	private int yearWeek; //記録が何年何月何週のものかを判別する
	//質問内容テーブル
	private int question_id, qType; //question_id = Question.id(主キー)
	private String question, created_atQcont;
	
	//コンストラクタ--------------------------------------------
	public DailyDTO(int id, int user_id, String freeForm, String photo, String positive, int emotion_id, double negativeRate, double positiveRate, double activeIndex, int yearWeek, String update_at,
			String created_at, int question_id, String question, int qType, String created_atQcont) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.freeForm = freeForm;
		this.photo = photo;
		this.positive = positive;
		this.emotion_id = emotion_id;
		this.negativeRate = negativeRate;
		this.positiveRate = positiveRate;
		this.activeIndex = activeIndex;
		this.yearWeek = yearWeek;
		this.update_at = update_at;
		this.created_at = created_at;

		this.question_id = question_id;
		this.question = question;
		this.qType = qType;
		this.created_atQcont = created_atQcont;
	}
	//一応入力なしを作ってみた
	public DailyDTO() {
		super();
		this.id = 0;
		this.user_id = 0;
		this.freeForm = "";
		this.photo = "";
		this.positive = "";
		this.emotion_id = 0;
		this.negativeRate = 0;
		this.positiveRate = 0;
		this.activeIndex = 0;
		this.yearWeek = 0;
		this.update_at = "";
		this.created_at = "";
		
		this.question_id = 0;
		this.question = "";
		this.qType = 0;
		this.created_atQcont = "";
	}
	
	//ゲッタ、セッタ------------------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public int getEmotion_id() {
		return emotion_id;
	}
	public void setEmotion_id(int emotion_id) {
		this.emotion_id = emotion_id;
	}
	public double getNegativeRate() {
		return negativeRate;
	}
	public void setNegativeRate(double negativeRate) {
		this.negativeRate = negativeRate;
	}
	public double getPositiveRate() {
		return positiveRate;
	}
	public void setPositiveRate(double positiveRate) {
		this.positiveRate = positiveRate;
	}
	public double getActiveIndex() {
		return activeIndex;
	}
	public void setActiveIndex(double activeIndex) {
		this.activeIndex = activeIndex;
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
	public int getqType() {
		return qType;
	}
	public void setqType(int qType) {
		this.qType = qType;
	}
	public String getCreated_atQcont() {
		return created_atQcont;
	}
	public void setCreated_atQcont(String created_atQcont) {
		this.created_atQcont = created_atQcont;
	}
	public int getYearWeek() {
		return yearWeek;
	}
	public void setYearWeek(int yearWeek) {
		this.yearWeek = yearWeek;
	}
	
}
