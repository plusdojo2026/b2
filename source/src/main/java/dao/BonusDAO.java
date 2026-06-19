package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.BonusDTO;

public class BonusDAO {
	
	//ビンゴの初期登録
	public boolean createBingo(int user_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//新しいビンゴを作成
			String sql = "INSERT INTO Bingo(user_id) VALUES(1);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
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
	
	
	//ログインした際の登録
	//pos=現在位置
	public boolean bingoLogin(int pos) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "UPDATE Bingo SET ?=1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//現在位置をカラム名に代入
			pStmt.setString(1,"day"+ String.valueOf(pos));	
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
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
	
	
	//ビンゴの内容を取得
	public BonusDTO selectBingo(int user_id) {
		Connection conn = null;
		BonusDTO record = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartwave?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "SELECT * FROM Bingo WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//現在位置をカラム名に代入
			pStmt.setInt(1,user_id);	
			
			
			// SELECT文を実行し、結果表を取得する
			
			ResultSet rs = pStmt.executeQuery();

			// selectした情報をリストに挿入
			rs.next();
			record = new BonusDTO(rs.getInt("id"),rs.getInt("user_id"),rs.getBoolean("day1"),rs.getBoolean("day2"),rs.getBoolean("day3"),rs.getBoolean("day4"),rs.getBoolean("day5"),rs.getBoolean("day6"),rs.getBoolean("day7"),rs.getBoolean("day8"),rs.getBoolean("day9"),rs.getBoolean("day10"),rs.getBoolean("day11"),rs.getBoolean("day12"),rs.getBoolean("day13"),rs.getBoolean("day14"),rs.getBoolean("day15"),rs.getBoolean("day16"),rs.getBoolean("day17"),rs.getBoolean("day18"),rs.getBoolean("day19"),rs.getBoolean("day20"),rs.getBoolean("day21"),rs.getBoolean("day22"),rs.getBoolean("day23"),rs.getBoolean("day24"),rs.getBoolean("day25"));
			
			// 結果を返す
			return record;
			
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
		return record;
	}
	
	
}