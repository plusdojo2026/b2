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

	public List<QuickDTO> select(QuickDTO week) {
		Connection conn = null;
		List<QuickDTO> quickList = new ArrayList<QuickDTO>();


			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("");			

				// SQL文を準備する
				String sql = "SELECT simpleRec, event, belief, result, reframe, txtFree, emotion_id, created_at "
								 + " FROM  SimpleRec ORDER BY simpleRec";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					QuickDTO Quick = new QuickDTO(
							rs.getInt("simpleRec"), 
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
	}		