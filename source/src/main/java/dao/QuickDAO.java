package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.QuickDTO;

public class QuickDAO{

	public List<QuickDTO> select(QuickDTO quick) {
		Connection conn = null;
		List<QuickDTO> quickList = new ArrayList<QuickDTO>();


			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");			

				// SQL文を準備する
				String sql = "SELECT id, user_id, event, belief, result, reframe, txtFree, emotion_id, created_at "
								 + " FROM  SimpleRec ORDER BY id";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					QuickDTO Quick = new QuickDTO(
							rs.getInt("id"), 
							rs.getInt("user_id"),
							rs.getString("event"), 
							rs.getString("belief"), 
							rs.getString("result"), 
							rs.getString("reframe"), 
							rs.getString("txtFree"), 
							rs.getInt("emotion_id"),  
							rs.getString("created_at")
							);
					quickList.add(Quick);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				quickList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				quickList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						quickList = null;
					}
				}
			}
			// 結果を返す
			return quickList;
		}
	
	public List<QuickDTO> selectid(int userid) {
		Connection conn = null;
		List<QuickDTO> quickList = new ArrayList<QuickDTO>();


			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");			

				// SQL文を準備する
				String sql = "SELECT id, user_id, event, belief, result, reframe, txtFree, emotion_id, created_at "
								 + " FROM  SimpleRec WHERE user_id = ? ORDER BY id";

				PreparedStatement pStmt = conn.prepareStatement(sql);
		

			        // SQL文に値をセットする
			        pStmt.setInt(1,userid);
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					QuickDTO Quick = new QuickDTO(
							rs.getInt("id"), 
							rs.getInt("user_id"),
							rs.getString("event"), 
							rs.getString("belief"), 
							rs.getString("result"), 
							rs.getString("reframe"), 
							rs.getString("txtFree"), 
							rs.getInt("emotion_id"),  
							rs.getString("created_at")
							);
					quickList.add(Quick);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				quickList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				quickList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						quickList = null;
					}
				}
			}
			// 結果を返す
			return quickList;
		}
	

	public boolean insert(QuickDTO dto) {
	    Connection conn = null;
	    boolean result = false;

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");


			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
	        // SQL文を準備する
	        String sql = "INSERT INTO SimpleRec(user_id,event, belief, result, reframe, txtFree, emotion_id) "
	                   + "VALUES (?,?, ?, ?, ?, ?,?)";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // SQL文に値をセットする
	        pStmt.setInt(1, dto.getUser_id());
	        pStmt.setString(2, dto.getEvent());
	        pStmt.setString(3, dto.getBelief());
	        pStmt.setString(4, dto.getResult());
	        pStmt.setString(5, dto.getReframe());
	        pStmt.setString(6, dto.getTxtFree());
	        pStmt.setInt(7, dto.getEmotion_id());


	        // SQL文を実行する（1件登録できたら成功）
	        if (pStmt.executeUpdate() == 1) {
	            result = true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        result = false;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        result = false;
	    } finally {
	        // データベースを切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                result = false;
	            }
	        }
	    }

	    // 結果を返す
	    return result;
	}

}