package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.TipsDTO;

public class TipsDAO {
	public TipsDTO Rondom() {
		Connection conn = null;
		TipsDTO tips =  null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");

			// SQL文を準備する
			String sql = "SELECT tips FROM Tips ORDER BY RAND() LIMIT 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			if (rs.next()){
				tips = new TipsDTO();
				tips.setTips(rs.getString("tips"));
			
			}
			
		//SQL実行時にエラーが起きたら入る
		} catch (SQLException e) {
			e.printStackTrace();
			tips = null;
		//JDBCドライバが見つからないときに入る
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			tips = null;
		//失敗しても成功しても入る
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					tips = null;
				}
			}
		}

		// 結果を返す
		return tips;
	}
}	
	