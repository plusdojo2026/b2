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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT id, userName, pw, loginStreak, daysTotalLogin, depthCurrent, currentPos, updated_at, created_at FROM Users WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, userId);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			if (rs.next()) {
				user = new UserDTO();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPw(rs.getString("pw"));
				user.setLoginStreak(rs.getInt("loginStreak"));
				user.setDaysTotalLogin(rs.getInt("daysTotalLogin"));
				user.setDepthCurrent(rs.getInt("depthCurrent"));
				user.setCurrentPos(rs.getInt("currentPos"));
				user.setUpdated_at(rs.getString("updated_at"));
				user.setCreated_at(rs.getString("created_at"));
			}
		//SQL実行時にエラーが起きたら入る
		} catch (SQLException e) {
			e.printStackTrace();
			user = null;
		//JDBCドライバが見つからないときに入る
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			user = null;
		//失敗しても成功しても入る
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					user = null;
				}
			}
		}

		// 結果を返す
		return user;
	}
	
//新規登録ページ
		//新規登録(引数で指定されたレコードを登録し、成功したらtrueを返す)
		public int insert(UserDTO user) {
			Connection conn = null;
			int id = 0;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000");

				// SQL文を準備する
				String sql = "INSERT INTO Users(userName, pw) VALUES (?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setString(1, user.getUserName());
					pStmt.setString(2, user.getPw());
				
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					
					
					//id取得
					// SQL文を準備する
					String sql2 = "SELECT id FROM Users WHERE userName=? AND pw=?";
					PreparedStatement pStmt2 = conn.prepareStatement(sql2);
					
					// SQL文を完成させる
					pStmt2.setString(1, user.getUserName());
					pStmt2.setString(2, user.getPw());
					
					// SELECT文を実行し、結果を取得する
					ResultSet rs2 = pStmt2.executeQuery();
					
					// SQL文を実行する→idを取得
					if (rs2.next()) {
						id = rs2.getInt("id");
						System.out.println("id="+id);
						return id;
					}
					
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
			return id;
		}
		
		//登録の重複を確認(usernameとpw)→trueの場合組み合わせが存在する
			public boolean existsUser(String UserName, String pw) {
				Connection conn = null;
				boolean exists = false;
				

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");

					// SQL文を準備する
					String sql = "SELECT COUNT(*) FROM Users WHERE userName=? AND pw=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
						pStmt.setString(1, UserName);
						pStmt.setString(2, pw);
					
					// SELECT文を実行し、結果を取得する
					ResultSet rs = pStmt.executeQuery();
					
					// SQL文を実行する
					if (rs.next()) {
						exists = rs.getInt(1) > 0; //COUNTの結果が1件以上なら「存在する」と判断する
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
				return exists;
			}
				
		
//ログイン	ページ
		//ユーザーログイン(引数で指定されたidpwでログイン成功ならユーザー情報を返し、失敗ならnullを返す）
		public UserDTO Login(String userName, String pw) {
			Connection conn = null;
			UserDTO user = null; //成功したらユーザー情報を詰めて返す

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");

				// SELECT文を準備する(ログイン判定+ユーザー情報取得)
				String sql = "SELECT id, userName, pw, loginStreak, daysTotalLogin, depthCurrent, currentPos, updated_at, created_at FROM Users WHERE userName=? AND pw=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, userName);
				pStmt.setString(2, pw);
				

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
				if (rs.next()) {
					user = new UserDTO();
					user.setId(rs.getInt("id"));
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
				user = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				user = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						user = null;
					}
				}
			}

			// 結果を返す
			return user;
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE Users SET daysTotalLogin= daysTotalLogin +1 WHERE id=?";
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
		
	    //　連続ログイン+1
		public boolean loginStreak(int userId) {
			Connection conn = null;
			boolean result = false;
	
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
	
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");
	
				// SQL文を準備する
				String sql = "UPDATE Users SET loginStreak = loginStreak +1 WHERE id=?";
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
		
		//　連続ログインリセット
				public boolean loginStreakReset(int userId) {
					Connection conn = null;
					boolean result = false;
			
					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");
			
						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
								"root", "password");
			
						// SQL文を準備する
						String sql = "UPDATE Users SET loginStreak = 1 WHERE id=?";
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
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");
	
					// SQL文を準備する
					String sql = "UPDATE Users SET depthCurrent = depthCurrent + 10 WHERE id=?";
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
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");
	
					// SQL文を準備する
					String sql = "UPDATE Users SET depthCurrent = depthCurrent + 1 WHERE id=?";
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
			
			//③連続ログインが途切れた時(深度を-30ｍ)
			public boolean decreaseDepth1(int userId) {
				Connection conn = null;
				boolean result = false;
	
				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");
	
					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");
	
					// SQL文を準備する(現在の深度を取得)
					String sql1 = "SELECT depthCurrent FROM Users WHERE id=?";
					PreparedStatement pStmt1 = conn.prepareStatement(sql1);
					pStmt1.setInt(1, userId);
					ResultSet rs = pStmt1.executeQuery();
					
					int depthCurrent = 0;
					if(rs.next()) {
						depthCurrent = rs.getInt("depthCurrent");
					}
			
					//深度がマイナスにならない
					int newDepth = depthCurrent -30; 
					if(newDepth < 0) { newDepth = 0; //深度がマイナスにならない
					}
					
					// 水深の更新
					String sql = "UPDATE Users SET depthCurrent = ? WHERE id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				
					pStmt.setInt(1, newDepth);
					pStmt.setInt(2, userId);
	
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

			//最終ログイン日の更新
			public boolean updatedLogin(int userId, String today) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");

					// SQL文を準備する
					String sql = "UPDATE Users SET updated_at = ? WHERE id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				
					pStmt.setString(1, today);
					pStmt.setInt(2, userId);

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
			
			//ビンゴposの更新
			public boolean loginPosUpdate(int userId, int pos) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
							"root", "password");
					
					//pos(ビンゴの日付の更新)
					pos += 1;
					
					// SQL文を準備する
					String sql = "UPDATE Users SET currentPos = " + pos + " WHERE id=?";
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
			//連続ログイン
			public void processLoginStreak(int userId) {
			    updateTotalLoginDays(userId);  // 通算ログイン +1
			    loginStreak(userId);           // 連続ログイン +1
			    addDepth10(userId);            // 深度 +10
			}
			//途切れた時
			public void processLoginBreak(int userId) {
			    updateTotalLoginDays(userId);  // 通算ログイン +1
			    loginStreakReset(userId);      // streak = 1
			    decreaseDepth1(userId);        // 深度 -30（マイナス防止）
			}

//　マイページ
		//　マイページ(userNameとpwの編集)
		public boolean updateUser(UserDTO user) {
			Connection conn = null;
			boolean result = false;
		
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
		
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"root", "password");
		
				// SQL文を準備する
				String sql = "UPDATE Users SET userName=?, pw=? WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
					
				// SQL文を完成させる
					pStmt.setString(1, user.getUserName());
					pStmt.setString(2, user.getPw());
					pStmt.setInt(3, user.getId());
		
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
