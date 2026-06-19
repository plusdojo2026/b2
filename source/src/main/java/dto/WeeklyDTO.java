package dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeeklyDTO implements Serializable {
	private int id;							// ID
	private int user_id;					// USER_ID
	private String weeklyRes;		// 週期間
	private String analysisCmt;	// 分析コメント
	private double avgPositive;	// 平均ポジティブ率
	private String moodType;		// 気分の浮き沈み
	private String created_at;		// 登録日
	private List<DailyDTO> dailyList;

	public WeeklyDTO(int id, int user_id,  String weeklyRes, String analysisCmt, double avgPositive, String moodType, String created_at, List<DailyDTO> dailyList) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.weeklyRes = weeklyRes;
		this.analysisCmt = analysisCmt;
		this.avgPositive = avgPositive;
		this.moodType = moodType;
		this.created_at = created_at;
		this.dailyList = dailyList;
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

	public String getWeeklyRes() {
		return weeklyRes;
	}
	public void setWeeklyRes(String weeklyRes) {
		this.weeklyRes = weeklyRes;
	}

	public String getAnalysisCmt() {
		return analysisCmt;
	}
	public void setAnalysisCmt(String analysisCmt) {
		this.analysisCmt = analysisCmt;
	}

	public double getAvgPositive() {
		return avgPositive;
	}
	public void setAvgPositive(double avgPositive) {
		this.avgPositive = avgPositive;
	}

	public String getMoodType() {
		return moodType;
	}
	public void setMoodType(String moodType) {
		this.moodType = moodType;
	}

	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public List<DailyDTO> getDailyList() {
		return dailyList;
	}
	public void setDailyList(List<DailyDTO> dailyList) {
		this.dailyList = dailyList;
	}

	public WeeklyDTO() {
		super();
		this.id = 0;
		this.user_id = 0;
		this.weeklyRes = "";
		this.analysisCmt = "";
		this.avgPositive = 0;
		this.moodType = "";
		this.created_at = "";
		this.dailyList = new ArrayList<>();
	}
}