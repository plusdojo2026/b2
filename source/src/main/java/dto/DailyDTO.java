package dto;

import java.io.Serializable;

public class DailyDTO implements Serializable {
	/*
	 * update_at, created_atはStringで実装、ソートなど作るときにはdate型へ変更必須
	 * idをidとしている、命名変更することはできる
	 */

	//フィールド----------------------------------------------
	private int id, userId, emotionId, typeId;
	private String freeForm, photo, positive;
	private double negativeRate, positiveRate, activeIndex;
	
	//コンストラクタ--------------------------------------------
	public DailyDTO(int id, int userId, String freeForm, String photo, String positive, int emotionId, int typeId, double negativeRate, double positiveRate, double activeIndex) {
		super();
		this.id = id;
		this.userId = userId;
		this.freeForm = freeForm;
		this.photo = photo;
		this.positive = positive;
		this.emotionId = emotionId;
		this.typeId = typeId;
		this.negativeRate = negativeRate;
		this.positiveRate = positiveRate;
		this.activeIndex = activeIndex;
	}
	//一応入力なしを作ってみた
	public DailyDTO() {
		super();
		this.id = 0;
		this.userId = 0;
		this.freeForm = "";
		this.photo = "";
		this.positive = "";
		this.emotionId = 0;
		this.typeId = 0;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEmotionId() {
		return emotionId;
	}
	public void setEmotionId(int emotionId) {
		this.emotionId = emotionId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
