package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DailyDTO;

public class DailyDAO {

		public List<DailyDTO> select() {
		Connection conn = null;
		List<DailyDTO> todayRev = new ArrayList<DailyDTO>();

		try {
			// JDBCドライバ読み込み、データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * 
			 * 毎日記録結果ページで当日の情報を呼び出すメソッド
			 * ・毎日入力テーブルからcreated_atをDESCで一件とりだす
			 * ・多分todayRevは配列じゃなくていいけど動けばよいのだ精神
			 */
			
			String sql = "SELECT id, user_id, freeForm, photo, positive, emotion_id, type_id, negativeRate, positiveRate, activeIndex, updated_at, created_at FROM DailyRec ORDER BY created_at DESC LIMIT 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				//結果をコレクションに格納
				DailyDTO dto = new DailyDTO(rs.getInt("id"), rs.getInt("user_id"),rs.getString("freeForm"), rs.getString("photo"), rs.getString("positive"), rs.getInt("emotion_id"), rs.getInt("type_id"), rs.getDouble("negativeRate"), rs.getDouble("positiveRate"), rs.getDouble("activeIndex"));
				todayRev.add(dto);
			}

			rs.close();
		
		//例外処理
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
		return todayRev;
	}


	public boolean insert(DailyDTO daily) {
		Connection conn = null;
		boolean result = false;


		try {
			// JDBCドライバ読み込み、データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * 毎日記録入力画面の登録メソッド
			 * ・DiaryRecテーブルに入力した情報を登録
			 */
			
			//1.DiaryRecテーブル
			String sql = "INSERT INTO DailyRec VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, yearWeek(CURDATE(),1), NOW(), NOW()";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//分析したデータを格納する
			pStmt.setInt(1, daily.getUser_id());
			if (daily.getFreeForm() != null) {
				pStmt.setString(2, daily.getFreeForm());
			} else {
				pStmt.setString(2, "");
			}
			if (daily.getPhoto() != null) {
				pStmt.setString(3, daily.getPhoto());
			} else {
				pStmt.setString(3, "");
			}
			if (daily.getPositive() != null) {
				pStmt.setString(4, daily.getPositive());
			} else {
				pStmt.setString(4, "");
			}
			pStmt.setInt(5, daily.getEmotion_id());
			pStmt.setInt(6, daily.getType_id());
			pStmt.setDouble(7, daily.getNegativeRate());
			pStmt.setDouble(8, daily.getPositiveRate());
			pStmt.setDouble(9, daily.getActiveIndex());
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}

		
		//例外処理
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * ~SQL文~
			 * 1.DiaryRecテーブルに挿入
			 * 2.Questionテーブルに挿入
			 * 3.QuestionAnsテーブルに挿入
			 * 途中
			 */
			
			//1.DiaryRecテーブル
			String sql1 = "UPDATE DailyRec SET freeForm=?, photo=?, positive=?, emotion_id=?, update_at=NOW(), created_at=NOW() "
					+ "WHERE id=?";
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
			// if (daily.getUpdate_at() != null) {
			// 	pStmt1.setString(4, daily.getUpdate_at());
			// } else {
			// 	pStmt1.setString(4, "");
			// }
			// if (daily.getCreated_at() != null) {
			// 	pStmt1.setString(5, daily.getCreated_at());
			// } else {
			// 	pStmt1.setString(5, "");
			// }
			
			// SQL文を実行する
			if (pStmt1.executeUpdate() == 1) {
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