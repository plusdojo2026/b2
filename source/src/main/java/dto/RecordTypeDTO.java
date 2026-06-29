package dto;

import java.io.Serializable;

public class RecordTypeDTO implements Serializable {

	private int id;
	private String typeRes;
	private String resultPicture;
	private String typeComment;

	public RecordTypeDTO(int id, String typeRes, String resultPicture, String typeComment) {
		this.id = id;
		this.typeRes = typeRes;
		this.resultPicture = resultPicture;
		this.typeComment = typeComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeRes() {
		return typeRes;
	}

	public void setTypeRes(String typeRes) {
		this.typeRes = typeRes;
	}

	public String getResultPicture() {
		return resultPicture;
	}

	public void setResultPicture(String resultPicture) {
		this.resultPicture = resultPicture;
	}

	public String getTypeComment() {
		return typeComment;
	}

	public void setTypeComment(String typeComment) {
		this.typeComment = typeComment;
	}

	
}