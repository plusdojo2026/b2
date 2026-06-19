package dto;

import java.io.Serializable;

public class DailyDTO implements Serializable {
	/*
	 * update_at, created_atはStringで実装、ソートなど作るときにはdate型へ変更必須
	 * idをdailyIdとしている、命名変更することはできる
	 */

	//フィールド----------------------------------------------
	private int dailyId, userId, emotionId, typeId;
	private String freeForm, photo, positive, update_at, created_at;
	private double negativeRate, positiveRate, activeIndex;
	private int yearWeek; //記録が何年何月何週のものかを判別する
	
	//コンストラクタ--------------------------------------------
	public DailyDTO(int dailyId, int userId, String freeForm, String photo, String positive, int emotionId, int typeId, double negativeRate, double positiveRate, double activeIndex, int yearWeek, String update_at,
			String created_at) {
		super();
		this.dailyId = dailyId;
		this.userId = userId;
		this.freeForm = freeForm;
		this.photo = photo;
		this.positive = positive;
		this.emotionId = emotionId;
		this.typeId = typeId;
		this.negativeRate = negativeRate;
		this.positiveRate = positiveRate;
		this.activeIndex = activeIndex;
		this.yearWeek = yearWeek;
		this.update_at = update_at;
		this.created_at = created_at;
	}
	//一応入力なしを作ってみた
	public DailyDTO() {
		super();
		this.dailyId = 0;
		this.userId = 0;
		this.freeForm = "";
		this.photo = "";
		this.positive = "";
		this.emotionId = 0;
		this.typeId = 0;
		this.negativeRate = 0;
		this.positiveRate = 0;
		this.activeIndex = 0;
		this.yearWeek = 0;
		this.update_at = "";
		this.created_at = "";
	}
	
	//ゲッタ、セッタ------------------------------------------------
	public int getDailyId() {
		return dailyId;
	}
	public void setDailyId(int dailyId) {
		this.dailyId = dailyId;
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
	public int getYearWeek() {
		return yearWeek;
	}
	public void setYearWeek(int yearWeek) {
		this.yearWeek = yearWeek;
	}	
}
