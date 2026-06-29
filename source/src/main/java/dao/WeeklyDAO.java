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
import java.util.List;

import dto.DailyDTO;
import dto.WeeklyDTO;

public class WeeklyDAO {
	
	//週間結果ページ表示時に週間のDBから該当の週のデータをもってくるメソッド。
	public List<WeeklyDTO> select(WeeklyDTO week) {
		Connection conn = null;
		List<WeeklyDTO> weekList = new ArrayList<WeeklyDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");

//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
			
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

			String sqlDay = "SELECT * FROM DailyRec WHERE user_id = ? AND yearWeek = ?";

				PreparedStatement pStmtDay = conn.prepareStatement(sqlDay);
				pStmtDay.setInt(1, week.getUser_id());
				pStmtDay.setInt(2, yearWeek);

				ResultSet rsDay = pStmtDay.executeQuery();

				List<DailyDTO> dailyList = new ArrayList<>();
				while (rsDay.next()) {
				    DailyDTO daily = new DailyDTO();

				    daily.setId(rsDay.getInt("id"));
				    daily.setUser_id(rsDay.getInt("user_id"));
				    daily.setFreeForm(rsDay.getString("freeForm"));
				    daily.setPhoto(rsDay.getString("photo"));
				    daily.setPositive(rsDay.getString("positive"));
				    daily.setEmotion_id(rsDay.getInt("emotion_id"));
				    daily.setType_id(rsDay.getInt("type_id"));
				    daily.setNegativeRate(rsDay.getDouble("negativeRate"));
				    daily.setPositiveRate(rsDay.getDouble("positiveRate"));
				    daily.setActiveIndex(rsDay.getDouble("activeIndex"));
				    daily.setYearWeek(rsDay.getInt("yearWeek"));
				    dailyList.add(daily);
				}
			
			// SQL文を準備する
			String sqlWeek = "SELECT wr.id, wr.user_id, wr.weeklyRes, wr.avgPositive, "
					+ "wc.analysisCmt, ms.moodType, wr.created_at "
					+ "FROM WeekRes wr "
					+ "JOIN WeekCmt wc ON wr.weekCmt_id = wc.id "
					+ "JOIN MoodSwings ms ON wr.moodSwings_id = ms.id "
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
	
	public List<WeeklyDTO> latestSelect(int userId) {
		Connection conn = null;
		List<WeeklyDTO> weekList = new ArrayList<WeeklyDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");

//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
//	        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//	        			"root", "password");
			
			String latestSql =
				    "SELECT weeklyRes FROM WeekRes " +
				    "WHERE user_id = ? " +
				    "ORDER BY created_at DESC LIMIT 1";

				PreparedStatement latestStmt = conn.prepareStatement(latestSql);
				latestStmt.setInt(1, userId);

				ResultSet latestRs = latestStmt.executeQuery();

				String weeklyRes = null;

				if (latestRs.next()) {
				    weeklyRes = latestRs.getString("weeklyRes");
				}
				// null対策
				if (weeklyRes == null) {
					return weekList;
				}

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

			String sqlDay = "SELECT * FROM DailyRec WHERE user_id = ? AND yearWeek = ?";

				PreparedStatement pStmtDay = conn.prepareStatement(sqlDay);
				pStmtDay.setInt(1, userId);
				pStmtDay.setInt(2, yearWeek);

				ResultSet rsDay = pStmtDay.executeQuery();

				List<DailyDTO> dailyList = new ArrayList<>();
				while (rsDay.next()) {
				    DailyDTO daily = new DailyDTO();

				    daily.setId(rsDay.getInt("id"));
				    daily.setUser_id(rsDay.getInt("user_id"));
				    daily.setFreeForm(rsDay.getString("freeForm"));
				    daily.setPhoto(rsDay.getString("photo"));
				    daily.setPositive(rsDay.getString("positive"));
				    daily.setEmotion_id(rsDay.getInt("emotion_id"));
				    daily.setType_id(rsDay.getInt("type_id"));
				    daily.setNegativeRate(rsDay.getDouble("negativeRate"));
				    daily.setPositiveRate(rsDay.getDouble("positiveRate"));
				    daily.setActiveIndex(rsDay.getDouble("activeIndex"));
				    daily.setYearWeek(rsDay.getInt("yearWeek"));
				    dailyList.add(daily);
				}
			
			// SQL文を準備する
			String sqlWeek = "SELECT wr.id, wr.user_id, wr.weeklyRes, wr.avgPositive, "
					+ "wc.analysisCmt, ms.moodType, wr.created_at "
					+ "FROM WeekRes wr "
					+ "JOIN WeekCmt wc ON wr.weekCmt_id = wc.id "
					+ "JOIN MoodSwings ms ON wr.moodSwings_id = ms.id "
					+ "WHERE wr.weeklyRes = ? AND wr.user_id = ?";
			
			PreparedStatement pStmtWeek = conn.prepareStatement(sqlWeek);
			
			//SQL文を完成させる//pStmt.setInt(1, weeklyRes);
			pStmtWeek.setString(1, weeklyRes);
			pStmtWeek.setInt(2, userId);
			
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

	        // データベースに接続
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");
	        
//	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
//	        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//	        			"root", "password");
	       //その日が属す週の各データをとってくる
	       String sql = "SELECT negativeRate, positiveRate, activeIndex FROM DailyRec WHERE user_id = ? AND yearWeek = ?";
	        

	       // 空Listを用意
	       List<Double> positiveRate = new ArrayList<>();
	       List<Double> negativeRate = new ArrayList<>();
	       List<Double> activeIndex = new ArrayList<>();
	       
	       try (PreparedStatement pStmt = conn.prepareStatement(sql)) {

	       pStmt.setInt(1, daily.getUser_id());
	       pStmt.setInt(2, daily.getYearWeek());

	       try (ResultSet rs = pStmt.executeQuery()) {
	       // 1行ずつ取り出してListに追加
	       while (rs.next()) {
	           positiveRate.add(rs.getDouble("positiveRate"));
	           negativeRate.add(rs.getDouble("negativeRate"));
	           activeIndex.add(rs.getDouble("activeIndex"));
	       }
	       //空データ対策
	       if (positiveRate.isEmpty()) {
	    	   return false;
	       }

	       }}
	        //平均ポジティブ率(double)
			double avgPositive = positiveRate.stream().mapToDouble(Double::doubleValue).average().orElse(0);
			//平均ネガティブ率(double)
			double avgNegative = negativeRate.stream().mapToDouble(Double::doubleValue).average().orElse(0);
			
	      //５段階評価で考える？0~20:凪，21~40：穏やか，41~60：平均的，61~80：やや激しめ，81~100： ジェットコースター
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
	        //感情バランス指数：正なら明るく負なら暗い
	        double emoBalance = avgPositive - avgNegative;

	        //変動指数 = 活性指数の標準偏差
	        double std = 0;
	        if (!activeIndex.isEmpty()) {
	        	double sum = 0;
	        	for (double value : activeIndex) {
	        		sum += (value - avgActiveIndex) * (value - avgActiveIndex);
	        	}
	        	std = Math.sqrt(sum / activeIndex.size());
	        }

	        //分析コメント
	        int analysisCmt = 2;
	        if (std < 10 && emoBalance >= 0) {
	        	analysisCmt = 1; //一貫して明るい
	        } else if (std < 10 && emoBalance < 0) {
	        	analysisCmt = 2; //一貫して暗い
	        } else if (10 <= std && std < 20 && emoBalance >= 0) {
	        	analysisCmt = 3; //そこそこ波はありつつ明るめ
	        } else if (10 <= std && std < 20 && emoBalance < 0) {
	        	analysisCmt = 4; //そこそこ波はありつつ暗め
	        } else if (20 <= std && emoBalance >= 0) {
	        	analysisCmt = 5; //不安定ながらも明るい
	        } else {
	        	analysisCmt = 6; //不安定だし暗い
	        }
	        
	        //yearWeekを日付の文字列に変換
	        int yearWeek = daily.getYearWeek();
	        int year = yearWeek / 100;
	        int week = yearWeek % 100;

	        LocalDate start = LocalDate.of(year, 1, 4).with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week).with(ChronoField.DAY_OF_WEEK, 1);
	        LocalDate end = start.plusDays(6);
	        String weeklyRes = start + "~" + end;


	        conn.setAutoCommit(false);
	     // UPDATE
	     String sqlUpdate = "UPDATE WeekRes SET weekCmt_id = ?, avgPositive = ?, moodSwings_id = ? "
	                      + "WHERE user_id = ? AND weeklyRes = ?";

	     int updated = 0;

	     try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
	         ps.setInt(1, analysisCmt);
	         ps.setDouble(2, avgPositive);
	         ps.setInt(3, moodType);
	         ps.setInt(4, daily.getUser_id());
	         ps.setString(5, weeklyRes);
	         updated = ps.executeUpdate();
	     }
	     

	     // UPDATE件数が0ならINSERT
	     if (updated == 0) {
	         String sqlInsert = "INSERT INTO WeekRes (user_id, weeklyRes, weekCmt_id, avgPositive, moodSwings_id, created_at) "
	                          + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

	         try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
	             ps.setInt(1, daily.getUser_id());
	             ps.setString(2, weeklyRes);
	             ps.setInt(3, analysisCmt);
	             ps.setDouble(4, avgPositive);
	             ps.setInt(5, moodType);
	             ps.executeUpdate();
	         }
	     }
	     conn.commit();
	     result = true;



		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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

	//振り返り画面用ページング付き一覧表示
	public List<WeeklyDTO> selectAll(int user_Id, int page) {

	    Connection conn = null;
	    List<WeeklyDTO> weekList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");

//	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
//	        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//	        			"root", "password");
	        
	        //  最新の created_at を取得
	        String latestSql =
	            "SELECT created_at FROM WeekRes " +
	            "WHERE user_id = ? " +
	            "ORDER BY created_at DESC LIMIT 1";

	        PreparedStatement latestStmt = conn.prepareStatement(latestSql);
	        latestStmt.setInt(1, user_Id);

	        ResultSet latestRs = latestStmt.executeQuery();

	        String latestCreatedAt = null;
	        if (latestRs.next()) {
	            latestCreatedAt = latestRs.getString("created_at");
	        }

	        //  最新週のdaily取得
	        List<DailyDTO> latestDailyList = new ArrayList<>();

	        if (latestCreatedAt != null) {

	            String weeklyResSql =
	                "SELECT weeklyRes FROM WeekRes " +
	                "WHERE user_id = ? AND created_at = ?";

	            PreparedStatement wrStmt = conn.prepareStatement(weeklyResSql);
	            wrStmt.setInt(1, user_Id);
	            wrStmt.setString(2, latestCreatedAt);

	            ResultSet wrRs = wrStmt.executeQuery();

	            if (wrRs.next()) {
	                String weeklyRes = wrRs.getString("weeklyRes");

	                String startDateStr = weeklyRes.split("~")[0];

	                LocalDate date = LocalDate.parse(startDateStr);

	                WeekFields wf = WeekFields.ISO;
	                int year = date.get(wf.weekBasedYear());
	                int week = date.get(wf.weekOfWeekBasedYear());
	                int yearWeek = Integer.parseInt(String.format("%d%02d", year, week));

	                // Daily取得
	                String dailySql =
	                    "SELECT * FROM DailyRec " +
	                    "WHERE user_id = ? AND yearWeek = ? " +
	                    "LIMIT 7";

	                PreparedStatement dstmt = conn.prepareStatement(dailySql);
	                dstmt.setInt(1, user_Id);
	                dstmt.setInt(2, yearWeek);

	                ResultSet drs = dstmt.executeQuery();

	                while (drs.next()) {
	                    DailyDTO d = new DailyDTO();

	                    d.setId(drs.getInt("id"));
	                    d.setUser_id(drs.getInt("user_id"));
	                    d.setPositiveRate(drs.getDouble("positiveRate"));
	                    d.setNegativeRate(drs.getDouble("negativeRate"));

	                    latestDailyList.add(d);
	                }
	            }
	        }
	        
	        // ページング
	        int limit = 5;
	        int offset = (page - 1) * limit;

	        String weekSql =
	            "SELECT wr.id, wr.user_id, wr.weeklyRes, wr.avgPositive, " +
	            "wc.analysisCmt, ms.moodType, wr.created_at " +
	            "FROM WeekRes wr " +
	            "JOIN WeekCmt wc ON wr.weekCmt_id = wc.id " +
	            "JOIN MoodSwings ms ON wr.moodSwings_id = ms.id " +
	            "WHERE wr.user_id = ? " +
	            "ORDER BY wr.created_at DESC " +
	            "LIMIT ? OFFSET ?";

	        PreparedStatement stmt = conn.prepareStatement(weekSql);
	        stmt.setInt(1, user_Id);
	        stmt.setInt(2, limit);
	        stmt.setInt(3, offset);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {

	            String createdAt = rs.getString("created_at");

	            List<DailyDTO> dailyList;

	            // 最新1件のみdaily付与
	            if (createdAt.equals(latestCreatedAt)) {
	                dailyList = new ArrayList<>(latestDailyList);
	            } else {
	                dailyList = new ArrayList<>();
	            }

	            WeeklyDTO weekly = new WeeklyDTO(
	                rs.getInt("id"),
	                rs.getInt("user_id"),
	                rs.getString("weeklyRes"),
	                rs.getString("analysisCmt"),
	                rs.getDouble("avgPositive"),
	                rs.getString("moodType"),
	                rs.getString("created_at"),
	                dailyList
	            );

	            weekList.add(weekly);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        weekList = null;
	    } finally {
	        if (conn != null) {
	            try { conn.close(); } catch (SQLException e) {}
	        }
	    }

	    return weekList;
	}

	// WeekRes総件数カウント（ページング用）
	public int countAll(int user_Id) {
	    int count = 0;
	    Connection conn = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "b2", "7grzQ32C9PWe9pdD");

//	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?"
//	        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//	        			"root", "password");
	        
	        String sql = "SELECT COUNT(*) FROM WeekRes WHERE user_id = ?";

	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, user_Id);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (conn != null) conn.close(); } catch (SQLException e) {}
	    }

	    return count;
	}

}