package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.WeeklyDTO;

public class WeeklyDAO {
	public List<WeeklyDTO> select(WeeklyDTO week) {
		Connection conn = null;
		List<WeeklyDTO> weekList = new ArrayList<WeeklyDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT wr.weekRes_id, wr.weeklyRes, wr.avgPositive, "
					+ "wc.analysisCmt, ms.moodType, wr.created_at "
					+ "FROM WeekRes wr "
					+ "JOIN WeekCmt wc ON wr.analysisCmt = wc.weekCmt_id "
					+ "JOIN MoodSwings ms ON wr.moodType = ms.moodSwings_id "
					+ "WHERE wr.weekRes_id = 2";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SQL文を完成させる
			//pStmt.setInt(1, week.getWeekRes_id());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				WeeklyDTO weekly = new WeeklyDTO(
						rs.getInt("weekRes_id"), 
						rs.getString("weeklyRes"), 
						rs.getString("analysisCmt"), 
						rs.getDouble("avgPositive"), 
						rs.getString("moodType"), 
						rs.getString("created_at")
						);
				weekList.add(weekly);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			weekList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			weekList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					weekList = null;
				}
			}
		}
		// 結果を返す
		return weekList;
	}
	public void aggregate() {
		
	}
}