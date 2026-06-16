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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT WeekRes_id, weeklyRes, analysisCmt, avgPositive, moodType, created_at "
							 + "FROM WeekRes "
							 + "JOIN WeekCmt ON WeekRes.analysisCmt = WeekCmt.analysisCmt "
							 + "JOIN MoodSwings ON WeekRes.moodType = MoodSwings.moodType "
							 + "ORDER BY weekRes_id";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				WeeklyDTO weekly = new WeeklyDTO(
						rs.getInt("weekRes"), 
						rs.getString("weeklyRes"), 
						rs.getInt("analysisCmt"), 
						rs.getDouble("avgPositive"), 
						rs.getInt("moodType"), 
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
}