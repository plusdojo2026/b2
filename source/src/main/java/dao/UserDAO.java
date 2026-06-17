package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO {
//ユーザー情報を取得
	public UserDTO findById(int userId) {
		Connection conn = null;
		UserDTO user =  null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT user_id, userName, pw, loginStreak, daysTotalLogin, depthCurrent, currentPos, updated_at, created_at FROM Users WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, userId);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			if (rs.next()) {
				user = new UserDTO();
				user.setUser_id(rs.getInt("user_id"));
				user.setUserName(rs.getString("userName"));
				user.setPw(rs.getString("pw"));
				user.setLoginStreak(rs.getInt("loginStreak"));
				user.setDaysTotalLogin(rs.getInt("daysTotalLogin"));
				user.setDepthCurrent(rs.getInt("depthCurrent"));
				user.setCurrentPos(rs.getInt("currentPos"));
				user.setUpdated_at(rs.getString("updated_at"));
				user.setCreated_at(rs.getString("created_at"));
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
		return user;
	}
	
//新規登録ページ
		//新規登録(引数で指定されたレコードを登録し、成功したらtrueを返す)
		public boolean insert(UserDTO user) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO Users(userName, pw) VALUES (?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setString(1, user.getUserName());
					pStmt.setString(2, user.getPw());
				
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
		
//ログイン	ページ
		//ユーザーログイン(引数で指定されたidpwでログイン成功ならtrueを返す）
		public boolean isLoginOK(UserDTO user) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SELECT文を準備する
				String sql = "SELECT count(*) FROM Users WHERE userName=? AND pw=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, user.getUserName());
				pStmt.setString(2, user.getPw());
				

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
				rs.next();
				if (rs.getInt("count(*)") == 1) {
					loginResult = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}

//　ホームページ	
		//　通算ログイン
		public boolean updateTotalLoginDays(int userId) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET daysTotalLogin= daysTotalLogin +1 WHERE user_id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
			
				pStmt.setInt(1, userId);

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
		
	    //　連続ログイン
		public boolean loginStreak(int userId) {
			Connection conn = null;
			boolean result = false;
	
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
	
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
	
				// SQL文を準備する
				String sql = "UPDATE Users SET loginStreak = loginStreak +1 WHERE user_id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setInt(1, userId);
	
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
			
		//　水深①②③
			//　①連続ログイン成功時(深度を+10)
			public boolean addDepth10(int userId) {
				Connection conn = null;
				boolean result = false;
	
				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");
	
					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");
	
					// SQL文を準備する
					String sql = "UPDATE Users SET depthCurrent = depthCurrent + 10 WHERE user_id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				
					pStmt.setInt(1, userId);
	
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
		
			//②簡易記録記入時(深度を+1)
			public boolean addDepth1(int userId) {
				Connection conn = null;
				boolean result = false;
	
				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");
	
					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");
	
					// SQL文を準備する
					String sql = "UPDATE Users SET depthCurrent = depthCurrent + 1 WHERE user_id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				
					pStmt.setInt(1, userId);
	
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
			
			//③連続ログインが途切れた時(深度を-30ｍ、マイナスな値かどうかはｓｅｒｖｌｅｔの方で判断）
			public boolean decreaseDepth1(int userId) {
				Connection conn = null;
				boolean result = false;
	
				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");
	
					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");
	
					// SQL文を準備する
					String sql = "UPDATE Users SET depthCurrent = depthCurrent - 30 WHERE user_id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				
					pStmt.setInt(1, userId);
	
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

//　マイページ
		//　マイページ
		public boolean updateUser(UserDTO user) {
			Connection conn = null;
			boolean result = false;
		
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
		
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
		
				// SQL文を準備する
				String sql = "UPDATE Users SET userName=?, pw=? WHERE user_id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
					
				// SQL文を完成させる
					pStmt.setString(1, user.getUserName());
					pStmt.setString(2, user.getPw());
					pStmt.setInt(3, user.getUser_id());
		
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
		
}
