package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

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

		public AnalysisDTO analyze(ArrayList<Integer> point, ArrayList<String> emoType) {
		Connection conn = null;
		List<AnalysisDTO> result = new ArrayList<AnalysisDTO>();

		int[] topEmo = new int[7];
		/*
		 * ABC項目
		 * 0 = anger
		 * 1 = confuse
		 * 2 = depression-dejection
		 * 3 = fatigue
		 * 4 = tension-anxiety
		 * 5 = vigor-activity
		 * 6 = friendliness
		 * 
		 */

		double negative, positive, negativeRate, positiveRate, activeIndex;
		int emoBalance = 1;
		/*
		 * 感情バランス： 0 = ネガティブ、 1 = 普通、 2 = ポジティブ
		 */

		try {
			// JDBCドライバ読み込み、データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			/*
			 * ~SQL文~
			 * 引数：point[14], emoType[14]
			 * 返り値：typeId, negativeRate, positiveRate, activeIndex
			 * ・ABC項目ごとに点数を合算し、その点が高い順のリストを作成して並べ替える
			 * ・
			 * ※現在はソートの関係で同点の場合は登録順の早いものが選ばれる
			 */
			
			String sql = "SELECT r.id, r.user_id, typeRes, resultPicture, typeComment, qType FROM RecordType AS r JOIN Question AS q ON r.id = qType WHERE typeRes=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//点数を集計する
			for (int i = 0; i < point.size(); i++) {
				switch (emoType.get(i)) {
					case anger:
						topEmo[0] += point.get(i);
						negative += point.get(i);
						break;
					case confuse:
						topEmo[1] += point.get(i);
						negative += point.get(i);
						break;
					case depression-dejection:
						topEmo[2] += point.get(i);
						negative += point.get(i);
						break;
					case fatigue:
						topEmo[3] += point.get(i);
						negative += point.get(i);
						break;
					case tension-anxiety:
						topEmo[4] += point.get(i);
						negative += point.get(i);
						break;
					case vigor-activity:
						topEmo[5] += point.get(i);
						positive += point.get(i);
						break;
					case friendliness:
						topEmo[6] += point.get(i);
						positive += point.get(i);
						break;
				}
			}

			//最も点数の高いABC項目を感情タイプ判別の結果としてSQLに格納
			Arrays.sort(topEmo);
			
			if (daily.getTypeRes() != null) {
				pStmt.setString(1, daily.getTypeRes());
			} else {
				pStmt.setString(1, "");
			}

			//ポジティブ率、ネガティブ率、感情活性指数を算出
			negativeRate = negative / 16 * 100;
			positiveRate = positive / 40 * 100;
			activeIndex = (positive + negative) / 2;

			//ネガティブ率とポジティブ率の比較
			if (negativeRate > positiveRate) {
				emoBalance = 0;
			} else if (negativeRate < positiveRate) {
				emoBalance = 2;
			} else {
				emoBalance = 1;
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
		return new AnalysisDTO(
			typeId,
			negativeRate,
			positiveRate,
			activeIndex,
			emoBalance
		);
	}
}
