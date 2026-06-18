package dto;

import java.io.Serializable;

public class QuickDTO implements Serializable{
	private int id; //ID
	private int user_id; //ユーザーID
	private String event; //出来事
	private String belief; //信念
	private String result; //結果
	private String reframe; //ポジティブに変換
	private String txtFree; //とにかく記入
	private int emotion_id; // 感情タグ
	private String created_at; //登録日


public QuickDTO(int id,int user_id,String event,String belief,String result,String reframe,String txtFree,int emotion_id,String created_at) {
	super();
	this.id = id;
	this.user_id = user_id;
	this.event = event;
	this.belief = belief;
	this.result = result;
	this.reframe = reframe;
	this.txtFree = txtFree;
	this.emotion_id = emotion_id;
	this.created_at = created_at;
}

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

public String getEvent() {
	return event;
}

public void setEvent(String event) {
	this.event = event;
}

public String getBelief() {
	return belief;
}

public void setBelief(String belief) {
	this.belief = belief;
}

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}

public String getReframe() {
	return reframe;
}

public void setReframe(String reframe) {
	this.reframe = reframe;
}

public String getTxtFree() {
	return txtFree;
}

public void setTxtFree(String txtFree) {
	this.txtFree = txtFree;
}

public int getEmotion_id() {
	return emotion_id;
}

public void setEmotion_id(int emotion_id) {
	this.emotion_id = emotion_id;
}

public String getCreated_at() {
	return created_at;
}

public void setCreated_at(String created_at) {
	this.created_at = created_at;
}

public QuickDTO() {
	super();
	this.id = 0;
	this.user_id = 0;
	this.event = "";
	this.belief = "";
	this.result = "";
	this.reframe = "";
	this.txtFree = "";
	this.emotion_id = 0;
	this.created_at = "";
}
	
}


