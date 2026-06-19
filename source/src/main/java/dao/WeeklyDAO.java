package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dto.DailyDTO;
import dto.WeeklyDTO;

public class WeeklyDAO {
	//週間結果ページ表示時に週間のDBから該当の週のデータをもってくるメソッド。
	//weeklyResを渡したい。該当のWeeklyとDailyのデータを返したい。
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

			
			String weeklyRes = week.getWeeklyRes();
			//最初の日を取り出す

			String yearweek = weeklyRes.split("~")[0];
			//LocalDateに変換
			LocalDate date = LocalDate.parse(yearweek);

			//年・月・日を取り出す
			int year = date.getYear();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			LocalDate date2 = LocalDate.of(year, month, day);

			//年とISO週番号を出す
			WeekFields wf = WeekFields.ISO;
			int yearNum = date2.get(wf.weekBasedYear());
			int weekNum = date2.get(wf.weekOfWeekBasedYear());

			//YYYYWWに変換
			String StYearWeek = String.format("%d%02d", yearNum, weekNum);
			int yearWeek = Integer.parseInt(StYearWeek);

			String sqlDay = "SELECT * FROM DailyRec WHERE user_id = ? AND yearWeek = ? ORDER BY created_at ASC";

				PreparedStatement pStmtDay = conn.prepareStatement(sqlDay);
				pStmtDay.setInt(1, week.getUser_id());
				pStmtDay.setInt(2, yearWeek);

				ResultSet rsDay = pStmtDay.executeQuery();

				List<DailyDTO> dailyList = new ArrayList<>();

				while (rsDay.next()) {

				    DailyDTO daily = new DailyDTO();

				    daily.setDailyId(rsDay.getInt("id"));
				    daily.setUserId(rsDay.getInt("user_id"));
				    daily.setFreeForm(rsDay.getString("freeForm"));
				    daily.setPhoto(rsDay.getString("photo"));
				    daily.setPositive(rsDay.getString("positive"));
				    daily.setEmotionId(rsDay.getInt("emotion_id"));
				    daily.setTypeId(rsDay.getInt("type_id"));
				    daily.setNegativeRate(rsDay.getDouble("negativeRate"));
				    daily.setPositiveRate(rsDay.getDouble("positiveRate"));
				    daily.setActiveIndex(rsDay.getDouble("activeIndex"));
				    daily.setYearWeek(rsDay.getInt("yearWeek"));
				    daily.setUpdate_at(rsDay.getString("update_at"));
				    daily.setCreated_at(rsDay.getString("created_at"));

				    dailyList.add(daily);
				}
			
			// SQL文を準備する//idではなくweeklyResで指定する形に変更すること。"WHERE wr.weeklyRes = ?"
			String sqlWeek = "SELECT wr.id, wr.user_id, wr.weeklyRes, wr.avgPositive, "
					+ "wc.analysisCmt, ms.moodType, wr.created_at "
					+ "FROM WeekRes wr "
					+ "JOIN WeekCmt wc ON wr.weekCmt_id = wc.weekCmt_id "
					+ "JOIN MoodSwings ms ON wr.moodSwings_id = ms.moodSwings_id "
					+ "WHERE wr.weeklyRes = ? AND wr.user_id = ?";
			
			PreparedStatement pStmtWeek = conn.prepareStatement(sqlWeek);
			
			//SQL文を完成させる//pStmt.setInt(1, weeklyRes);
			pStmtWeek.setString(1, weeklyRes);
			pStmtWeek.setInt(2, week.getUser_id());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmtWeek.executeQuery();

			while (rs.next()) {

			    WeeklyDTO weekly = new WeeklyDTO(
			        rs.getInt("id"),
			        rs.getInt("user_id"),
			        rs.getString("weeklyRes"),
			        rs.getString("analysisCmt"),
			        rs.getDouble("avgPositive"),
			        rs.getString("moodType"),
			        rs.getString("created_at"),
			        new ArrayList<>(dailyList)
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
	
	//毎日記録登録時に該当の日が含まれる週間のDBに更新・新規作成を入れるメソッド
	public boolean aggregate(DailyDTO daily) {
		Connection conn = null;
		boolean result = false;
		
		try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続//後でデータベース変更しておくこと
	        conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/heartwave?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");
	        
	        //その日が属す週の各データをとってくる
	       //String sql = SELECT typeId, positiveRate, activeIndex FROM DailyRec WHERE WHERE user_id = ? AND yearWeek = ? ORDER BY created_at ASC"
	        
	        //平均ポジティブ率(double)//1行目はダミー後で消す
			List<Double> positiveRate = new ArrayList<>(Arrays.asList(0.8, 0.7, 0.9, 0.8, 0.7, 0.9, 0.8));
			double avgPositive = positiveRate.stream().mapToDouble(Double::doubleValue).average().orElse(0) * 100;
			//平均ネガティブ率(double)//1行目はダミー後で消す
			List<Double> negativeRate = new ArrayList<>(Arrays.asList(0.8, 0.7, 0.9, 0.8, 0.7, 0.9, 0.8));
			double avgNegative = negativeRate.stream().mapToDouble(Double::doubleValue).average().orElse(0) * 100;
			
	      //５段階評価で考える？0~20:凪，21~40：穏やか，41~60：平均的，61~80：やや激しめ，81~100： ジェットコースター
	        List<Double> activeIndex = new ArrayList<>(Arrays.asList(50.0, 40.0, 60.0, 70.0, 80.0, 30.0, 20.0));
			double avgActiveIndex = activeIndex.stream().mapToDouble(Double::doubleValue).average().orElse(0);
	        int moodType = 1;
	        if (avgActiveIndex <= 20) {
	        	moodType = 1;
	        } else if (avgActiveIndex <= 40) {
	        	moodType = 2;
	        } else if (avgActiveIndex <= 60) {
	        	moodType = 3;
	        } else if (avgActiveIndex <= 80) {
	        	moodType = 4;
	        } else {
	        	moodType = 5;
	        }
	        //感情バランス指数
	        double emoBalance = avgPositive - avgNegative;
	      //分析した式を後で以下に入れること//細かい設定は後で考えましょう
	        int analysisCmt = 2;
	        //変動指数 = 活性指数の標準偏差
	        System.out.println(moodType);
	        double sum = 0;
	        for (double value : activeIndex) {
	            sum += (value - avgActiveIndex) * (value - avgActiveIndex);
	        }
	        double temp = Math.sqrt(sum/activeIndex.size());
	        if (temp <= 20 && emoBalance < 1) {
	        	analysisCmt = 1;
	        } else if (temp <= 40 && emoBalance < 1) {
	        	analysisCmt = 2;
	        } else if (temp <= 60 && emoBalance < 1) {
	        	analysisCmt = 3;
	        } else if (temp <= 80 && emoBalance < 1) {
	        	analysisCmt = 4;
	        } else {
	        	analysisCmt = 5;
	        }
	        
	        //yearWeekを日付の文字列に変換
	        int yearWeek = 202615; //dailyのyearWeek。DailyDTOから渡されるので後で消す。

	        int year = yearWeek / 100;
	        int week = yearWeek % 100;

	        LocalDate start = LocalDate.of(year, 1, 4).with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week).with(ChronoField.DAY_OF_WEEK, 1);
	        LocalDate end = start.plusDays(6);
	        String weeklyRes = start + "~" + end;

	        //BEGIN
	        conn.setAutoCommit(false);

	        //DELETE
	        String sqlDelete = "DELETE FROM WeekRes WHERE weeklyRes = ? AND user_id = ?";

			try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
				ps.setString(1, weeklyRes);
				ps.setInt(2, daily.getUserId());
	            ps.executeUpdate();
	        }

	        //INSERT//user_idの扱い？
	        String sqlInsert = "INSERT INTO WeekRes (weeklyRes, analysisCmt, avgPositive, moodType, created_at) "
	                         + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

	        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
	            ps.setString(1, weeklyRes);
				ps.setInt(2, analysisCmt);
				ps.setDouble(3, avgPositive);
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