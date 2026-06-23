package dto;

import java.io.Serializable;

public class DailyDTO implements Serializable {
	/*
	 * update_at, created_atはStringで実装、ソートなど作るときにはdate型へ変更必須
	 * idをidとしている、命名変更することはできる
	 */

	//フィールド----------------------------------------------
	private int id, user_id, emotion_id, type_id;
	private String freeForm, photo, positive;
	private double negativeRate, positiveRate, activeIndex;
	
	//コンストラクタ--------------------------------------------
	public DailyDTO(int id, int user_id, String freeForm, String photo, String positive, int emotion_id, int type_id, double negativeRate, double positiveRate, double activeIndex) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.freeForm = freeForm;
		this.photo = photo;
		this.positive = positive;
		this.emotion_id = emotion_id;
		this.type_id = type_id;
		this.negativeRate = negativeRate;
		this.positiveRate = positiveRate;
		this.activeIndex = activeIndex;
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
		this.type_id = 0;
		this.negativeRate = 0;
		this.positiveRate = 0;
		this.activeIndex = 0;
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
	public int getEmotion_id() {
		return emotion_id;
	}
	public void setEmotion_id(int emotion_id) {
		this.emotion_id = emotion_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
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
}
