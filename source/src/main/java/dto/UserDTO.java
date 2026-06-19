package dto;

public class UserDTO {
	private int id; // ID
	private String userName; // ユーザーネーム
	private String pw; // パスワード
	private int loginStreak; // 連続ログイン日数
	private int daysTotalLogin; // ログイン日数
	private int depthCurrent; // 水深
	private int currentPos; // 現在位置
	private String updated_at; // 最終ログイン日時
	private String created_at; //登録日
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getLoginStreak() {
		return loginStreak;
	}
	public void setLoginStreak(int loginStreak) {
		this.loginStreak = loginStreak;
	}
	public int getDaysTotalLogin() {
		return daysTotalLogin;
	}
	public void setDaysTotalLogin(int daysTotalLogin) {
		this.daysTotalLogin = daysTotalLogin;
	}
	public int getDepthCurrent() {
		return depthCurrent;
	}
	public void setDepthCurrent(int depthCurrent) {
		this.depthCurrent = depthCurrent;
	}
	public int getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public UserDTO(int id, String userName, String pw, int loginStreak, int daysTotalLogin, int depthCurrent,
			int currentPos, String updated_at, String created_at) {
		this.id = id;
		this.userName = userName;
		this.pw = pw;
		this.loginStreak = loginStreak;
		this.daysTotalLogin = daysTotalLogin;
		this.depthCurrent = depthCurrent;
		this.currentPos = currentPos;
		this.updated_at = updated_at;
		this.created_at = created_at;
	}
	
	public UserDTO() {
		this.id = 0;
		this.userName = "";
		this.pw = "";
		this.loginStreak = 0;
		this.daysTotalLogin = 0;
		this.depthCurrent = 0;
		this.currentPos = 0;
		this.updated_at = "";
		this.created_at = "";
	}
	
}
