package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DailyDTO;
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
			String sql = "SELECT wr.id, wr.user_id, wr.weeklyRes, wr.avgPositive, "
					+ "wc.analysisCmt, ms.moodType, wr.created_at "
					+ "FROM WeekRes wr "
					+ "JOIN WeekCmt wc ON wr.weekCmt_id = wc.weekCmt_id "
					+ "JOIN MoodSwings ms ON wr.moodSwings_id = ms.moodSwings_id "
					+ "WHERE wr.id = 1";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SQL文を完成させる
			//pStmt.setInt(1, week.getid());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				WeeklyDTO weekly = new WeeklyDTO(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
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
	public boolean aggregate(DailyDTO daily) {
		Connection conn = null;
		boolean result = false;
		
		try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続
	        conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/heartwave?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");
	        
	        String weeklyRes = "";
	        int analysisCmt = 2;
	        int avgPositive = 80;
	        int moodType = 3;

	        //BEGIN
	        conn.setAutoCommit(false);

	        //DELETE
	        String sqlDelete = "DELETE FROM WeekRes WHERE weeklyRes = ?";

			try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
	            ps.setString(1, weeklyRes);
	            ps.executeUpdate();
	        }

	        //INSERT
	        String sqlInsert = "INSERT INTO WeekRes (weeklyRes, analysisCmt, avgPositive, moodType, created_at) "
	                         + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

	        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
	            ps.setString(1, weeklyRes);
				ps.setInt(2, analysisCmt);
				ps.setInt(3, avgPositive);
				ps.setInt(4, moodType);
	            ps.executeUpdate();
	        }

	        //COMMIT
	        conn.commit();
	        result = true;

	    } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		return result;
	}
}