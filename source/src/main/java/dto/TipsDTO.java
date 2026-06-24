package dto;

public class TipsDTO {
	private int id; // ID
	private String tipsTitle; // タイトル
	private String tips; // Tips
	private String created_at; // 登録日
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipsTitle() {
		return tipsTitle;
	}
	public void setTipsTitle(String tipsTitle) {
		this.tipsTitle = tipsTitle;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public TipsDTO(int id, String tipsTitle, String tips, String created_at) {
		super();
		this.id = id;
		this.tipsTitle = tipsTitle;
		this.tips = tips;
		this.created_at = created_at;
	}	
		public TipsDTO() {
			this.id = 0;
			this.tipsTitle = "";
			this.tips = "";
			this.created_at = "";
	}
	
	
	
}