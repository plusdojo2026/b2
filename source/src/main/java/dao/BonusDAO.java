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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//新しいビンゴを作成
			String sql = "INSERT INTO Bingo(user_id,day1) VALUES(?,1);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,user_id);	
			
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
	public boolean bingoLogin(int pos, int user_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "UPDATE Bingo SET day" + String.valueOf(pos) + "=1 WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,user_id);	
			
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
	
	
	
	public boolean setCount(int count, int user_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "UPDATE Bingo SET bingoCount =? WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,user_id);	
			pStmt.setInt(2,count);	
			
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
	
	
	
	public boolean bingoReset(int user_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "UPDATE Bingo SET day1=0, day2=0, day3=0, day4=0, day5=0, day6=0, day7=0, day8=0, day9=0, day10=0, day11=0, day12=0, day13=0, day14=0, day15=0, day16=0, day17=0, day18=0, day19=0, day20=0, day21=0, day22=0, day23=0, day24=0, day25=0, bingoCount=0 WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,user_id);				
			
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//今日のビンゴを登録
			String sql = "SELECT * FROM Bingo WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//現在位置をカラム名に代入
			pStmt.setInt(1,user_id);	
			
			
			// SELECT文を実行し、結果表を取得する
			
			ResultSet rs = pStmt.executeQuery();

			// selectした情報をリストに挿入
			rs.next();
			record = new BonusDTO(rs.getInt("id"),rs.getInt("user_id"),rs.getBoolean("day1"),rs.getBoolean("day2"),rs.getBoolean("day3"),rs.getBoolean("day4"),rs.getBoolean("day5"),rs.getBoolean("day6"),rs.getBoolean("day7"),rs.getBoolean("day8"),rs.getBoolean("day9"),rs.getBoolean("day10"),rs.getBoolean("day11"),rs.getBoolean("day12"),rs.getBoolean("day13"),rs.getBoolean("day14"),rs.getBoolean("day15"),rs.getBoolean("day16"),rs.getBoolean("day17"),rs.getBoolean("day18"),rs.getBoolean("day19"),rs.getBoolean("day20"),rs.getBoolean("day21"),rs.getBoolean("day22"),rs.getBoolean("day23"),rs.getBoolean("day24"),rs.getBoolean("day25"),rs.getInt("bingoCount"));
			
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
	
	
	
	
	
	//ログインしていなかった日数
	public int selectNotlogin(int user_id) {
		Connection conn = null;
		int record = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//ログインできなかった日付を取得
			String sql = "SELECT DATEDIFF(CURDATE(), DATE(updated_at)) as days_not_logged_in FROM Users WHERE id=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1,user_id);	
			
			
			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// selectした情報をリストに挿入
			rs.next();
			record = rs.getInt("days_not_logged_in");
			
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