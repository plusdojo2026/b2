package dto;
import java.io.Serializable;

public class WeeklyDTO implements Serializable {
	private int weekRes;				// ID
	private String weeklyRes;		// 週期間
	private int analysisCmt;			// 分析コメント
	private double avgPositive;	// 平均ポジティブ率
	private int moodType;			// 気分の浮き沈み
	private String created_at;		// 登録日

	public WeeklyDTO(int weekRes,  String weeklyRes, int analysisCmt, double avgPositive, int moodType, String created_at) {
		super();
		this.weekRes = weekRes;
		this.weeklyRes = weeklyRes;
		this.analysisCmt = analysisCmt;
		this.avgPositive = avgPositive;
		this.moodType = moodType;
		this.created_at = created_at;
	}

	public int getWeekRes() {
		return weekRes;
	}
	public void setWeekRes(int weekRes) {
		this.weekRes = weekRes;
	}

	public String getWeeklyRes() {
		return weeklyRes;
	}
	public void setWeeklyRes(String weeklyRes) {
		this.weeklyRes = weeklyRes;
	}

	public int getAnalysisCmt() {
		return analysisCmt;
	}
	public void setAnalysisCmt(int analysisCmt) {
		this.analysisCmt = analysisCmt;
	}

	public double getAvgPositive() {
		return avgPositive;
	}
	public void setAvgPositive(double avgPositive) {
		this.avgPositive = avgPositive;
	}

	public int getMoodType() {
		return moodType;
	}
	public void setMoodType(int moodType) {
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
		this.weekRes = 0;
		this.weeklyRes = "";
		this.analysisCmt = 0;
		this.avgPositive = 0;
		this.moodType = 0;
		this.created_at = "";
		
	}
}