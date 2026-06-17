package dto;
import java.io.Serializable;

public class WeeklyDTO implements Serializable {
	private int weekRes_id;			// ID
	private String weeklyRes;		// 週期間
	private String analysisCmt;			// 分析コメント
	private double avgPositive;	// 平均ポジティブ率
	private String moodType;	// 気分の浮き沈み
	private String created_at;		// 登録日

	public WeeklyDTO(int weekRes_id,  String weeklyRes, String analysisCmt, double avgPositive, String moodType, String created_at) {
		super();
		this.weekRes_id = weekRes_id;
		this.weeklyRes = weeklyRes;
		this.analysisCmt = analysisCmt;
		this.avgPositive = avgPositive;
		this.moodType = moodType;
		this.created_at = created_at;
	}

	public int getWeekRes_id() {
		return weekRes_id;
	}
	public void setWeekRes_id(int weekRes_id) {
		this.weekRes_id = weekRes_id;
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
	public WeeklyDTO() {
		super();
		this.weekRes_id = 0;
		this.weeklyRes = "";
		this.analysisCmt = "";
		this.avgPositive = 0;
		this.moodType = "";
		this.created_at = "";
		
	}
}