package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.QuestionDTO;

public class QuestionDAO {

	public List<QuestionDTO> select() {
		Connection conn = null;
		List<QuestionDTO> qList = new ArrayList<QuestionDTO>();

		try {
			// JDBCドライバ読み込み、データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * ~SQL文~
			 * ・質問をABC項目ごとに2問取り出す
			 * 
			 */
			
			String sql = "SELECT id, question, qType, created_at FROM Question WHERE qType=? ORDER BY RAND() LIMIT 2";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//質問をABC項目ごとに2問取り出す
			for (int qType = 1; qType <= 7; qType++) {

				pStmt.setInt(1, qType);

				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					//結果をコレクションに格納
					QuestionDTO dto = new QuestionDTO(rs.getInt("id"),rs.getString("question"), rs.getInt("qType"), rs.getString("created_at"));
					qList.add(dto);
				}

				rs.close();
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
		return qList;
	}

}
