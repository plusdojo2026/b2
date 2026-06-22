package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BonusDAO;
import dao.UserDAO;
import dto.UserDTO;



	@WebServlet("/HomeServlet")
	public class HomeServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			// セッションを取得
			HttpSession session = request.getSession();
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			if (session.getAttribute("user") == null) { //セッションにuser(DTO)があるか確認
				response.sendRedirect("/b2/LoginServlet");
				return;
			}
			
			
			//ログイン中のユーザーを取得→キャスト
			UserDTO loginUser = (UserDTO) session.getAttribute("user"); //セッションからUserDTOを取得
						
			// リクエストパラメータを取得する(フォームから送られた新しいuserNameとpwを取得)
			request.setCharacterEncoding("UTF-8");
						
			//セッションから最終ログイン日時を取得
			String lastloginStr = loginUser.getUpdated_at();  
			String lastloginDate = lastloginStr.substring(0,10); //0~10文字目を取得
			LocalDate lastLogin = LocalDate.parse(lastloginDate);
						
			//今日の日付を取得
			LocalDate today = LocalDate.now();
						
			//ログインチェック
			UserDAO uDao = new UserDAO();
			
			// テスト
			System.out.println("===ログインテスト ===");
			System.out.println("DB updated_at (raw): " + lastloginStr);
			System.out.println("parsed lastLogin: " + lastLogin);
			System.out.println("today: " + today);
			System.out.println("lastLogin.plusDays(1): " + lastLogin.plusDays(1));
						
			if(lastLogin.isEqual(today)) {
			//今日ログイン済み何もしない
				System.out.println("条件1：今日すでにログイン済みor初回ログイン時");		
				
			}else if(lastLogin.plusDays(1).isEqual(today)) {
				//昨日ログイン　→　連続ログイン継続
				System.out.println("条件2：昨日ログイン（連続ログイン継続）");
				uDao.processLoginStreak(loginUser.getId());//通算ログイン(データ)
				
				//ビンゴの登録
				int pos = loginUser.getCurrentPos();
								
				BonusDAO bonus2 = new BonusDAO();
				boolean bonusRes = bonus2.bingoLogin(pos);
				if(!bonusRes) {
					System.out.println("ビンゴの登録に失敗");
				}else {
					//posの更新
					uDao.loginPosUpdate(loginUser.getId(), pos);
				}
							
			//連続ログインが途切れた時
			}else {
				System.out.println("→ 条件3：連続ログイン途切れ（深度 -30）");
				uDao.processLoginBreak(loginUser.getId());//通算ログイン(データ)	
							
					//ビンゴの登録
					int pos = loginUser.getCurrentPos();
							
					BonusDAO bonus2 = new BonusDAO();
					boolean bonusRes = bonus2.bingoLogin(pos);
					if(!bonusRes) {
						System.out.println("ビンゴの登録に失敗");
					}else {
						//posの更新
						uDao.loginPosUpdate(loginUser.getId(), pos);
					}
							
			}System.out.println("===================");
			
				//最終ログイン日を今日に更新
				uDao.updatedLogin(loginUser.getId(),today.toString());
				
				//DBの最新情報をセッションに反映
				UserDTO fresh = uDao.findById(loginUser.getId());
				session.setAttribute("user", fresh);
				
				//ログイン記録を画面表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
				dispatcher.forward(request, response);
			
		}	

		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request,response);	
		}
	}
