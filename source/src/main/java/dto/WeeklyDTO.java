package dto;
import java.io.Serializable;

public class WeeklyDTO implements Serializable {
	private int weekRes_id;			// ID
	private String weeklyRes;		// 週期間
	private int weekCmt_id;			// 分析コメント
	private double avgPositive;	// 平均ポジティブ率
	private int moodSwings_id;			// 気分の浮き沈み
	private String created_at;		// 登録日

	public WeeklyDTO(int weekRes_id,  String weeklyRes, int weekCmt_id, double avgPositive, int moodSwings_id, String created_at) {
		super();
		this.weekRes_id = weekRes_id;
		this.weeklyRes = weeklyRes;
		this.weekCmt_id = weekCmt_id;
		this.avgPositive = avgPositive;
		this.moodSwings_id = moodSwings_id;
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

	public int getWeekCmt_id() {
		return weekCmt_id;
	}
	public void setWeekCmt_id(int weekCmt_id) {
		this.weekCmt_id = weekCmt_id;
	}

	public double getAvgPositive() {
		return avgPositive;
	}
	public void setAvgPositive(double avgPositive) {
		this.avgPositive = avgPositive;
	}

	public int getMoodSwings_id() {
		return moodSwings_id;
	}
	public void setMoodSwings_id(int moodSwings_id) {
		this.moodSwings_id = moodSwings_id;
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
		this.weekCmt_id = 0;
		this.avgPositive = 0;
		this.moodSwings_id = 0;
		this.created_at = "";
		
	}
}