package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.DailyDTO;

public class DailyDAO {
	
	
	// 登録処理 引数:daily, 返り値:result
	public boolean insert(DailyDTO daily) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * ~SQL文~
			 * 1.DiaryRecテーブルに挿入
			 * 2.Questionテーブルに挿入
			 * 3.QuestionAnsテーブルに挿入
			 */
			
			//1.DiaryRecテーブル
			String sql1 = "INSERT INTO DailyRec VALUES (0, ?, ?, ?, 0, ?, ?)";
			PreparedStatement pStmt1 = conn.prepareStatement(sql1);

			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getFreeForm() != null) {
				pStmt1.setString(1, daily.getFreeForm());
			} else {
				pStmt1.setString(1, "");
			}
			if (daily.getPhoto() != null) {
				pStmt1.setString(2, daily.getPhoto());
			} else {
				pStmt1.setString(2, "");
			}
			if (daily.getPositive() != null) {
				pStmt1.setString(3, daily.getPositive());
			} else {
				pStmt1.setString(3, "");
			}
			if (daily.getUpdate_at() != null) {
				pStmt1.setString(4, daily.getUpdate_at());
			} else {
				pStmt1.setString(4, "");
			}
			if (daily.getCreated_at() != null) {
				pStmt1.setString(5, daily.getCreated_at());
			} else {
				pStmt1.setString(5, "");
			}
			
			// SQL文を実行する
			if (pStmt1.executeUpdate() == 1) {
				result = true;
			}
			
			//2.Questionテーブル
			String sql2 = "INSERT INTO Question VALUES (0, ?, ?)";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getQuestion() != null) {
				pStmt2.setString(1, daily.getQuestion());
			} else {
				pStmt2.setString(1, "");
			}
			if (daily.getCreated_atQcont() != null) {
				pStmt2.setString(2, daily.getCreated_atQcont());
			} else {
				pStmt2.setString(2, "");
			}
			
			// SQL文を実行する
			if (pStmt2.executeUpdate() == 1) {
				result = true;
			}
			
			//3.QuestionAnsテーブル
			String sql3 = "INSERT INTO QuestionAns VALUES (0, ?)";
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getCreated_atQans() != null) {
				pStmt3.setString(1, daily.getCreated_atQans());
			} else {
				pStmt3.setString(1, "");
			}
			
			// SQL文を実行する
			if (pStmt3.executeUpdate() == 1) {
				result = true;
			}
			
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

	
	public boolean update(DailyDTO daily) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * ~SQL文~
			 * 1.DiaryRecテーブルに挿入
			 * 2.Questionテーブルに挿入
			 * 3.QuestionAnsテーブルに挿入
			 */
			
			//1.DiaryRecテーブル
			String sql1 = "UPDATE DailyRec SET freeForm=?, photo=?, positive=?, emotion_id=?, update_at=?, created_at=? "
					+ "WHERE dailyRec_id=?";
			PreparedStatement pStmt1 = conn.prepareStatement(sql1);

			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getFreeForm() != null) {
				pStmt1.setString(1, daily.getFreeForm());
			} else {
				pStmt1.setString(1, "");
			}
			if (daily.getPhoto() != null) {
				pStmt1.setString(2, daily.getPhoto());
			} else {
				pStmt1.setString(2, "");
			}
			if (daily.getPositive() != null) {
				pStmt1.setString(3, daily.getPositive());
			} else {
				pStmt1.setString(3, "");
			}
			if (daily.getUpdate_at() != null) {
				pStmt1.setString(4, daily.getUpdate_at());
			} else {
				pStmt1.setString(4, "");
			}
			if (daily.getCreated_at() != null) {
				pStmt1.setString(5, daily.getCreated_at());
			} else {
				pStmt1.setString(5, "");
			}
			
			// SQL文を実行する
			if (pStmt1.executeUpdate() == 1) {
				result = true;
			}
			
			//2.Questionテーブル
			String sql2 = "UPDATE Question SET question=?, created_atQcont=? "
					+ "WHERE question_id=?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getQuestion() != null) {
				pStmt2.setString(1, daily.getQuestion());
			} else {
				pStmt2.setString(1, "");
			}
			if (daily.getCreated_atQcont() != null) {
				pStmt2.setString(2, daily.getCreated_atQcont());
			} else {
				pStmt2.setString(2, "");
			}
			
			// SQL文を実行する
			if (pStmt2.executeUpdate() == 1) {
				result = true;
			}
			
			//3.QuestionAnsテーブル
			String sql3 = "UPDATE QuestionAns SET created_atQans=? "
					+ "WHERE question_id=?";
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			// SQL文初期値を自動で入力する(Stringのみ)
			if (daily.getCreated_atQans() != null) {
				pStmt3.setString(1, daily.getCreated_atQans());
			} else {
				pStmt3.setString(1, "");
			}
			
			// SQL文を実行する
			if (pStmt3.executeUpdate() == 1) {
				result = true;
			}
			
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